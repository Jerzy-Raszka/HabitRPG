package com.example.habitrpg.repository;

import com.example.habitrpg.model.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    List<ToDo> findAllByAssignedUser_Username(String username);
}
