package com.example.habitrpg.controller;

import com.example.habitrpg.model.dto.CreateToDoDto;
import com.example.habitrpg.model.dto.ToDoDto;
import com.example.habitrpg.model.entity.ToDo;
import com.example.habitrpg.service.ToDoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public List<ToDoDto> getTodo(Authentication authentication) {
        return toDoService.getUserToDo();
    }

    @PostMapping
    public ResponseEntity<ToDo> addNewTodo(@Valid @RequestBody CreateToDoDto createToDoDto, Authentication authentication) {
        ToDo created = toDoService.createFromDto(createToDoDto, (String) authentication.getPrincipal());
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
