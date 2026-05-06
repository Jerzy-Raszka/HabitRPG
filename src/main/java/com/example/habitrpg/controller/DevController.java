package com.example.habitrpg.controller;

import com.example.habitrpg.model.dto.DateDto;
import com.example.habitrpg.service.DevService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("habitrpg/dev")
public class DevController {
    private final DevService devService;

    public DevController(DevService devService) {
        this.devService = devService;
    }

    @PostMapping("/time")
    public ResponseEntity<LocalDate> setMockedDate(@RequestBody DateDto dateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(devService.setMockedDate(dateDto.date()));
    }

    @DeleteMapping("/time")
    public ResponseEntity<Void> clearDate() {
        devService.clearMockedDate();
        return ResponseEntity.noContent().build();
    }
}
