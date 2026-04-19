package com.example.habitrpg.repository;

import com.example.habitrpg.model.dto.ToDoDto;
import com.example.habitrpg.model.entity.ToDo;
import com.example.habitrpg.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    Optional<ToDo> findByIdAndAssignedUserUsername(Integer id, String username);

    @Query("""
                            SELECT new com.example.habitrpg.model.dto.ToDoDto(
                                t.task,
                                t.description,
                                t.rewardXp,
                                t.rewardGold,
                                t.timeType,
                                t.completed,
                                t.deadline
                            )
                            FROM ToDo t
                            WHERE t.assignedUser = :assignedUser
            """)
    List<ToDoDto> findAllByAssignedUser(User assignedUser);
}
