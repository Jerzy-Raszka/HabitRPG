package com.example.habitrpg.util;

import com.example.habitrpg.model.enums.ToDoTimeType;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class TimeProvider {

    public Period currentPeriod(ToDoTimeType type) {
        return switch (type) {
            case DAILY -> new Period(today(), today());
            case WEEKLY -> new Period(startOfWeek(), endOfWeek());
            case MONTHLY -> new Period(startOfMonth(), endOfMonth());
            default -> null;
        };
    }

    public LocalDate today() {
        return LocalDate.now();
    }

    public LocalDate startOfWeek() {
        return today().with(DayOfWeek.MONDAY);
    }

    public LocalDate endOfWeek() {
        return today().with(DayOfWeek.SUNDAY);
    }

    public LocalDate startOfMonth() {
        return today().withDayOfMonth(1);
    }

    public LocalDate endOfMonth() {
        return today().withDayOfMonth(today().lengthOfMonth());
    }

}
