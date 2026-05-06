package com.example.habitrpg.util;

import com.example.habitrpg.model.enums.ToDoTimeType;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class TimeProvider {

    private LocalDate mockedDate;

    public void setMockedDate(LocalDate date) {
        mockedDate = date;
    }

    public void clearMockedDate() {
        mockedDate = null;
    }

    public Period currentPeriod(ToDoTimeType type) {
        return switch (type) {
            case DAILY -> new Period(today(), today());
            case WEEKLY -> new Period(startOfWeek(), endOfWeek());
            case MONTHLY -> new Period(startOfMonth(), endOfMonth());
            default -> null;
        };
    }

    public Period previousPeriod(ToDoTimeType type) {

        Period current = currentPeriod(type);

        return switch (type) {
            case DAILY -> new Period(current.start().minusDays(1), current.end().minusDays(1));
            case WEEKLY -> new Period(current.start().minusWeeks(1), current.end().minusWeeks(1));
            case MONTHLY -> new Period(current.start().minusMonths(1), current.end().minusMonths(1));
            default -> null;
        };
    }

    public LocalDate today() {
        return mockedDate != null ? mockedDate : LocalDate.now();
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
