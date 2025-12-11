package com.example.habitrpg.controller;

import com.example.habitrpg.model.dto.ToDoDto;
import com.example.habitrpg.model.entity.CreateToDoDto;
import com.example.habitrpg.model.entity.ToDo;
import com.example.habitrpg.service.ToDoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("habitrpg/todo")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDoDto> getTodo() {
        return toDoService.getAllToDo().stream().map(u -> new ToDoDto(u.getTask(), u.getDescription(), u.getRewardXp(), u.getRewardGold(), u.getTimeType(), u.isCompleted(), u.getDeadline())).toList();
    }

    @PostMapping
    public ResponseEntity<ToDo> addNewTodo(@Valid @RequestBody CreateToDoDto createToDoDto) {
        ToDo created = toDoService.createFromDto(createToDoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("{id}")
    public void switchStatus(@PathVariable Integer id) {
        toDoService.changeCompletionStatus(id);
    }

    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable Integer id) {
        toDoService.deleteTodo(id);
    }
}
