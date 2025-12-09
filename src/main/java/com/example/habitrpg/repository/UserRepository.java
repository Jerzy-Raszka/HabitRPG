package com.example.habitrpg.repository;

import com.example.habitrpg.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
