package com.example.habitrpg.repository;

import com.example.habitrpg.model.dto.UserProfileDto;
import com.example.habitrpg.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    void deleteByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("""
                SELECT new com.example.habitrpg.model.dto.UserProfileDto(
                    u.username,
                    u.hp,            
                    u.level,
                    u.xp,
                    u.gold
                )
                FROM User u
                WHERE u.username = :username
            """)
    Optional<UserProfileDto> findProfileByUsername(@Param("username") String username);
}
