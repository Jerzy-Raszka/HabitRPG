package com.example.habitrpg.controller;

import com.example.habitrpg.model.dto.CreateToDoDto;
import com.example.habitrpg.model.dto.ToDoDto;
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
        return toDoService.getUserToDo();
    }

    @PutMapping("{id}")
    public ResponseEntity<ToDoDto> completeTodo(@PathVariable Integer id) {
        ToDoDto completed = toDoService.completeTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(completed);
    }

    @PostMapping
    public ResponseEntity<ToDoDto> addNewTodo(@Valid @RequestBody CreateToDoDto createToDoDto) {
        ToDoDto created = toDoService.createFromDto(createToDoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable Integer id) {
        toDoService.deleteTodo(id);
    }
}
