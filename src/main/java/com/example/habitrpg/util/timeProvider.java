package com.example.habitrpg.util;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class timeProvider {

    public LocalDate today() {
        return LocalDate.now();
    }

    public LocalDate startOfWeek() {
        return today().with(DayOfWeek.MONDAY);
    }

    public LocalDate endOfWeek() {
        return today().with(DayOfWeek.SUNDAY);
    }

}
