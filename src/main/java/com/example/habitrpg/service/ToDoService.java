package com.example.habitrpg.service;

import com.example.habitrpg.model.entity.CreateToDoDto;
import com.example.habitrpg.model.entity.ToDo;
import com.example.habitrpg.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllToDo() {
        return toDoRepository.findAll();
    }

    public ToDo createFromDto(CreateToDoDto createToDoDto) {
        ToDo.Builder builder = new ToDo.Builder(createToDoDto.task());
        builder.assignedUser(createToDoDto.assignedUser());
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
