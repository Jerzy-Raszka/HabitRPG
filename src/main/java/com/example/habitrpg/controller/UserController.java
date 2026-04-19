package com.example.habitrpg.controller;

import com.example.habitrpg.model.dto.AmountDto;
import com.example.habitrpg.model.dto.UserProfileDto;
import com.example.habitrpg.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("habitrpg/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public UserProfileDto getUserProfile() {
        return userService.getUserProfile();
    }

    @PostMapping("/levelup")
    public void levelUp() {
        userService.levelUp();
    }

    @PostMapping("/xp")
    public void addXp(@Valid @RequestBody AmountDto amountDto) {
        userService.addXp(amountDto.amount());
    }

    @PostMapping("/gold/add")
    public void addGold(@Valid @RequestBody AmountDto amountDto) {
        userService.addGold(amountDto.amount());
    }

    @PostMapping("/gold/subtract")
    public void subtractGold(@Valid @RequestBody AmountDto amountDto) {
        userService.subtractGold(amountDto.amount());
    }

    @PostMapping("/dealdamage")
    public void dealDamage(@Valid @RequestBody AmountDto amountDto) {
        userService.dealDamage(amountDto.amount());
    }

    @PostMapping("/heal")
    public void heal(@Valid @RequestBody AmountDto amountDto) {
        userService.heal(amountDto.amount());
    }

    @DeleteMapping()
    public void deleteUser() {
        userService.deleteUser();
    }
}
