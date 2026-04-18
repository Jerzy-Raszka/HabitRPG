package com.example.habitrpg.service;

import com.example.habitrpg.model.entity.User;
import com.example.habitrpg.repository.UserRepository;
import com.example.habitrpg.security.CurrentUserProvider;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CurrentUserProvider currentUserProvider;

    public UserService(UserRepository userRepository, CurrentUserProvider currentUserProvider) {
        this.userRepository = userRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public void levelUp() {
        User currentUser = currentUserProvider.getCurrentUser();
        currentUser.addLevel();
        userRepository.save(currentUser);
    }

    public void addXp(Integer xp) {
        User currentUser = currentUserProvider.getCurrentUser();
        currentUser.addXp(xp);
        userRepository.save(currentUser);
    }

    public void addGold(Integer goldAmount) {
        User currentUser = currentUserProvider.getCurrentUser();
        currentUser.addGold(goldAmount);
        userRepository.save(currentUser);
    }

    public void subtractGold(Integer goldAmount) {
        User currentUser = currentUserProvider.getCurrentUser();
        currentUser.subtractGold(goldAmount);
        userRepository.save(currentUser);
    }

    public void dealDamage(Integer damageAmount) {
        User currentUser = currentUserProvider.getCurrentUser();
        currentUser.subtractHp(damageAmount);
        userRepository.save(currentUser);
    }

    public void heal(Integer healAmount) {
        User currentUser = currentUserProvider.getCurrentUser();
        currentUser.addHp(healAmount);
        userRepository.save(currentUser);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
