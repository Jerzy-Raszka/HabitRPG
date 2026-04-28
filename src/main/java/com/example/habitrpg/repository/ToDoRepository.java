package com.example.habitrpg.repository;

import com.example.habitrpg.model.entity.ToDo;
import com.example.habitrpg.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    Optional<ToDo> findByIdAndAssignedUserUsername(Integer id, String username);

    List<ToDo> findAllByAssignedUser(User assignedUser);
}
