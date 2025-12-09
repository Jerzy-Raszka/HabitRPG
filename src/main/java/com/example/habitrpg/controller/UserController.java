package com.example.habitrpg.controller;

import com.example.habitrpg.model.dto.AmountDto;
import com.example.habitrpg.model.dto.CreateUserDto;
import com.example.habitrpg.model.entity.User;
import com.example.habitrpg.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("habitrpg/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> addNewUser(@Valid @RequestParam CreateUserDto createUserDto) {
        User newUser = userService.createFromDTO(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("{id}/levelup")
    public void levelUp(@PathVariable Integer id) {
        userService.levelUp(id);
    }

    @PostMapping("{id}/xp")
    public void addXp(@PathVariable Integer id, @RequestParam AmountDto amountDto) {
        userService.addXp(id, amountDto.amount());
    }

    @PostMapping("{id}/gold/add")
    public void addGold(@PathVariable Integer id, @RequestParam AmountDto amountDto) {
        userService.addGold(id, amountDto.amount());
    }

    @PostMapping("{id}/gold/subtract")
    public void subtractGold(@PathVariable Integer id, @RequestParam AmountDto amountDto) {
        userService.subtractGold(id, amountDto.amount());
    }

    @PostMapping("{id}/heal")
    public void heal(@PathVariable Integer id, @RequestParam AmountDto amountDto) {
        userService.heal(id, amountDto.amount());
    }

    @PostMapping("{id}/dealdamage")
    public void dealDamage(@PathVariable Integer id, @RequestParam AmountDto amountDto) {
        userService.dealDamage(id, amountDto.amount());
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
