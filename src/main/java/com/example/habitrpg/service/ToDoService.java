package com.example.habitrpg.service;

import com.example.habitrpg.model.dto.CreateToDoDto;
import com.example.habitrpg.model.dto.ToDoDto;
import com.example.habitrpg.model.entity.ToDo;
import com.example.habitrpg.model.entity.User;
import com.example.habitrpg.model.enums.ToDoTimeType;
import com.example.habitrpg.repository.ToDoRepository;
import com.example.habitrpg.security.CurrentUserProvider;
import com.example.habitrpg.util.Period;
import com.example.habitrpg.util.TimeProvider;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final CurrentUserProvider currentUserProvider;
    private final TimeProvider timeProvider;

    public ToDoService(ToDoRepository toDoRepository, CurrentUserProvider currentUserProvider, TimeProvider timeProvider) {
        this.toDoRepository = toDoRepository;
        this.currentUserProvider = currentUserProvider;
        this.timeProvider = timeProvider;
    }

    public boolean streakCheck(ToDo todo) {
        if (todo.getLastRewardedAt() == null) {
            return false;
        }

        Period previous = timeProvider.previousPeriod(todo.getTimeType());
        LocalDate rewardDate = todo.getLastRewardedAt();

        return !rewardDate.isBefore(previous.start()) && !rewardDate.isAfter(previous.end());
    }

    public void updateStreak(ToDo todo) {
        if (todo.getTimeType() == null) {
            return;
        }

        if (streakCheck(todo)) {
            todo.setCurrentStreak(todo.getCurrentStreak() + 1);
        } else {
            todo.setCurrentStreak(1);
        }
    }

    public void giveRewards(User user, ToDo todo) {
        double multiplayer = switch (todo.getTimeType()) {
            case DAILY -> 0.1;
            case WEEKLY -> 0.3;
            case MONTHLY -> 1.0;
            default -> 0.0;
        };
        user.addXp((int) Math.round((todo.getRewardXp() * (1 + multiplayer * todo.getCurrentStreak()))));
        user.addGold((int) Math.round((todo.getRewardGold() * (1 + multiplayer * todo.getCurrentStreak()))));
    }

    public ToDoDto completeTodo(Integer id) {

        ToDo todo = getTodoIfOwnedByCurrentUser(id);

        if (isCompleted(todo)) {
            throw new RuntimeException("This task was already completed");
        }

        updateStreak(todo);

        todo.setLastRewardedAt(timeProvider.today());

        giveRewards(currentUserProvider.getCurrentUser(), todo);

        toDoRepository.save(todo);
        return mapToDto(todo);
    }

    //Check if task was completed in current period
    public boolean isCompleted(ToDo todo) {

        if (todo.getLastRewardedAt() == null) {
            return false;
        }

        Period period = timeProvider.currentPeriod(todo.getTimeType());

        LocalDate rewardDate = todo.getLastRewardedAt();

        return !rewardDate.isBefore(period.start()) && !rewardDate.isAfter(period.end());
    }

    //Find task by id and return it only if belongs to currently logged user - used for deleting todos
    private ToDo getTodoIfOwnedByCurrentUser(Integer id) {
        return toDoRepository.findByIdAndAssignedUserUsername(id, currentUserProvider.getCurrentUser().getUsername())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

    private ToDoDto mapToDto(ToDo todo) {

        boolean available = todo.getAvailableAt() == null || !timeProvider.today().isBefore(todo.getAvailableAt());
        boolean completed = todo.getTimeType() == ToDoTimeType.NONE ? todo.getLastRewardedAt() != null : isCompleted(todo);


        if (todo.getAvailableAt() != null) {
            available = !timeProvider.today().isBefore(todo.getAvailableAt());
        }

        boolean canBeCompleted = !completed;

        return new ToDoDto(
                todo.getId(),
                todo.getTask(),
                todo.getDescription(),
                todo.getRewardXp(),
                todo.getRewardGold(),
                todo.getTimeType(),
                completed,
                canBeCompleted,
                todo.getDeadline(),
                todo.getCurrentStreak(),
                available
        );
    }

    private void applyDefaultDeadline(ToDo toDo) {
        if (toDo.getDeadline() == null && toDo.getTimeType() != ToDoTimeType.NONE) {

            Period period = timeProvider.currentPeriod(toDo.getTimeType());

            if (period != null) {
                toDo.setDeadline(period.end());
            }
        }
    }

    public List<ToDoDto> getUserToDo() {
        return toDoRepository.findAllByAssignedUser(currentUserProvider.getCurrentUser()).stream().map(this::mapToDto).toList();
    }

    public ToDoDto createFromDto(CreateToDoDto createToDoDto) {

        User user = currentUserProvider.getCurrentUser();

        ToDo.Builder builder = new ToDo.Builder(createToDoDto.task());
        builder.assignedUser(user);
        if (createToDoDto.description() != null && !createToDoDto.description().isBlank()) {
            builder.description(createToDoDto.description());
        }
        if (createToDoDto.timeType() != null) {
            builder.timeType(createToDoDto.timeType());
        }
        if (createToDoDto.deadline() != null) {
            builder.deadline(createToDoDto.deadline());
        }
        if (createToDoDto.availableAt() != null) {
            builder.availableAt(createToDoDto.availableAt());
        }

        ToDo toDo = builder.build();

        applyDefaultDeadline(toDo);

        ToDo saved = toDoRepository.save(toDo);

        return mapToDto(saved);
    }


    public void deleteTodo(Integer id) {

        ToDo todo = getTodoIfOwnedByCurrentUser(id);
        toDoRepository.delete(todo);

    }
}
