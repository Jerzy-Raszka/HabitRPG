package com.example.habitrpg.service;

import com.example.habitrpg.model.dto.CreateToDoDto;
import com.example.habitrpg.model.entity.ToDo;
import com.example.habitrpg.model.entity.User;
import com.example.habitrpg.repository.ToDoRepository;
import com.example.habitrpg.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;

    public ToDoService(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    public List<ToDo> getAllToDo() {
        return toDoRepository.findAll();
    }

    public ToDo createFromDto(CreateToDoDto createToDoDto) {
        Integer userId = createToDoDto.assignedUserId();
        User assignedUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Assigned user not found: " + userId));

        ToDo.Builder builder = new ToDo.Builder(createToDoDto.task());
        builder.assignedUser(assignedUser);
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

        return toDoRepository.save(toDo);
    }

    public void changeCompletionStatus(Integer id) {
        ToDo todo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " not found"));
        todo.switchCompletionStatus();
        toDoRepository.save(todo);
    }

    public void deleteTodo(Integer id) {
        toDoRepository.deleteById(id);
    }
}
