package com.example.habitrpg.service;

import com.example.habitrpg.model.dto.ToDoDto;
import com.example.habitrpg.model.entity.ToDo;
import com.example.habitrpg.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository){this.toDoRepository = toDoRepository;}
    public List<ToDo> getAllToDo(){return toDoRepository.findAll();}
    public ToDo createFromDto(ToDoDto toDoDto){
        return toDoRepository.save(new ToDo(toDoDto.task(), toDoDto.deadline()));
    }

    public void insertToDo(ToDo toDo){
        toDoRepository.save(toDo);
    }

    public void changeCompletionStatus(Integer id){
        ToDo todo = toDoRepository.findById(id).orElseThrow(()->new RuntimeException(id + " not found"));
        todo.switchCompletionStatus();
        toDoRepository.save(todo);
    }

    public void deleteTodo(Integer id){
        toDoRepository.deleteById(id);
    }
}
