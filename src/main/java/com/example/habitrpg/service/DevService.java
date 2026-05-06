package com.example.habitrpg.service;

import com.example.habitrpg.util.TimeProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DevService {
    private final TimeProvider timeProvider;

    public DevService(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public LocalDate setMockedDate(LocalDate date) {
        timeProvider.setMockedDate(date);
        return date;
    }

    public void clearMockedDate() {
        timeProvider.clearMockedDate();
    }
}
