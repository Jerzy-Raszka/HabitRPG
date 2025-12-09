package com.example.habitrpg.service;

import com.example.habitrpg.model.dto.CreateUserDto;
import com.example.habitrpg.model.entity.User;
import com.example.habitrpg.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createFromDTO(CreateUserDto createUserDto) {
        User newUser = new User(createUserDto.username(), createUserDto.password());
        return userRepository.save(newUser);
    }

    public void levelUp(Integer id) {
        User currentUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " not found"));
        currentUser.addLevel();
        userRepository.save(currentUser);
    }

    public void addXp(Integer id, Integer xp) {
        User currentUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " not found"));
        currentUser.addXp(xp);
        userRepository.save(currentUser);
    }

    public void addGold(Integer id, Integer goldAmount) {
        User currentUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " not found"));
        currentUser.addGold(goldAmount);
        userRepository.save(currentUser);
    }

    public void subtractGold(Integer id, Integer goldAmount) {
        User currentUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " not found"));
        currentUser.subtractGold(goldAmount);
        userRepository.save(currentUser);
    }

    public void dealDamage(Integer id, Integer damageAmount) {
        User currentUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " not found"));
        currentUser.subtractHp(damageAmount);
        userRepository.save(currentUser);
    }

    public void heal(Integer id, Integer healAmount) {
        User currentUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " not found"));
        currentUser.addHp(healAmount);
        userRepository.save(currentUser);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
