package com.example.habitrpg.service;

import com.example.habitrpg.model.dto.CreateToDoDto;
import com.example.habitrpg.model.dto.ToDoDto;
import com.example.habitrpg.model.entity.ToDo;
import com.example.habitrpg.model.entity.User;
import com.example.habitrpg.repository.ToDoRepository;
import com.example.habitrpg.security.CurrentUserProvider;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final CurrentUserProvider currentUserProvider;

    public ToDoService(ToDoRepository toDoRepository, CurrentUserProvider currentUserProvider) {
        this.toDoRepository = toDoRepository;
        this.currentUserProvider = currentUserProvider;
    }

    private ToDo getTodoIfOwnedByCurrentUser(Integer id) {
        return toDoRepository.findByIdAndAssignedUserUsername(id, currentUserProvider.getCurrentUser().getUsername())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

    public List<ToDoDto> getUserToDo() {
        return toDoRepository.findAllByAssignedUser(currentUserProvider.getCurrentUser());
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

        ToDo toDo = builder.build();

        ToDo saved = toDoRepository.save(toDo);

        return new ToDoDto(
                saved.getTask(),
                saved.getDescription(),
                saved.getRewardXp(),
                saved.getRewardGold(),
                saved.getTimeType(),
                saved.isCompleted(),
                saved.getDeadline()
        );
    }

    public void changeCompletionStatus(Integer id) {

        ToDo todo = getTodoIfOwnedByCurrentUser(id);
        todo.switchCompletionStatus();
        toDoRepository.save(todo);

    }

    public void deleteTodo(Integer id) {
        ToDo todo = getTodoIfOwnedByCurrentUser(id);
        toDoRepository.delete(todo);
    }
}
