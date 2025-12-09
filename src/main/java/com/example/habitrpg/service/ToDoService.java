package com.example.habitrpg.service;

import com.example.habitrpg.model.dto.ToDoDto;
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

    public ToDo createFromDto(ToDoDto toDoDto) {
        ToDo.Builder builder = new ToDo.Builder(toDoDto.task());
        if (toDoDto.description() != null && !toDoDto.description().isBlank()) {
            builder.description(toDoDto.description());
        }
        if (toDoDto.timeType() != null) {
            builder.timeType(toDoDto.timeType());
        }
        if (toDoDto.deadline() != null) {
            builder.deadline(toDoDto.deadline());
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
