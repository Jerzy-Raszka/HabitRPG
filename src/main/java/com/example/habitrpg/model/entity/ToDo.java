package com.example.habitrpg.model.entity;

import com.example.habitrpg.model.enums.ToDoTimeType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String task;
    private String description;
    private Integer rewardXp;
    private Integer rewardGold;
    private ToDoTimeType timeType;
    private LocalDate deadline;
    private LocalDate lastRewardedAt;
    private LocalDate availableAt;
    private Integer currentStreak;
    @ManyToOne
    @JoinColumn(name = "assigned_user_user_id")
    private User assignedUser;

    public ToDo(Builder builder) {
        this.task = builder.task;
        this.description = builder.description;
        this.rewardXp = builder.rewardXp;
        this.rewardGold = builder.rewardGold;
        this.timeType = builder.timeType;
        this.deadline = builder.deadline;
        this.availableAt = builder.availableAt;
        this.currentStreak = builder.currentStreak;
        this.assignedUser = builder.assignedUser;
    }

    public ToDo() {

    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRewardXp() {
        return rewardXp;
    }

    public void setRewardXp(Integer rewardXp) {
        this.rewardXp = rewardXp;
    }

    public Integer getRewardGold() {
        return rewardGold;
    }

    public void setRewardGold(Integer rewardGold) {
        this.rewardGold = rewardGold;
    }

    public ToDoTimeType getTimeType() {
        return timeType;
    }

    public void setTimeType(ToDoTimeType timeType) {
        this.timeType = timeType;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getAvailableAt() {
        return availableAt;
    }

    public void setAvailableAt(LocalDate availableAt) {
        this.availableAt = availableAt;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadLine) {
        this.deadline = deadLine;
    }

    public LocalDate getLastRewardedAt() {
        return lastRewardedAt;
    }

    public void setLastRewardedAt(LocalDate lastRewardedAt) {
        this.lastRewardedAt = lastRewardedAt;
    }

    public Integer getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(Integer currentStreak) {
        this.currentStreak = currentStreak;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToDo other)) {
            return false;
        }

        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static class Builder {
        private final String task;
        private final Integer rewardXp = 100;
        private final Integer rewardGold = 50;
        private final boolean completed = false;
        private final LocalDate lastRewardedAt = null;
        private String description = "";
        private ToDoTimeType timeType = ToDoTimeType.NONE;
        private LocalDate deadline;
        private LocalDate availableAt;
        private Integer currentStreak;
        private User assignedUser;

        public Builder(String task) {
            this.task = Objects.requireNonNull(task, "Task must not be null");
            if (task.isBlank()) {
                throw new IllegalArgumentException("Task must not be blank");
            }
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder timeType(ToDoTimeType timeType) {
            this.timeType = timeType;
            return this;
        }


        public Builder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder availableAt(LocalDate availableAt) {
            this.availableAt = availableAt;
            return this;
        }

        
        public Builder assignedUser(User assignedUser) {
            this.assignedUser = Objects.requireNonNull(assignedUser, "User must be specified");
            return this;
        }

        public ToDo build() {
            return new ToDo(this);
        }

    }

}
