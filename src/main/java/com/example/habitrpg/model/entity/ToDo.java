package com.example.habitrpg.model.entity;

import com.example.habitrpg.model.enums.toDoTimeType;
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
    private toDoTimeType timeType;
    private boolean completed;
    private LocalDate deadline;
    @ManyToOne
    @JoinColumn(name = "assigned_user_user_id")
    private User assignedUser;

    public ToDo(Builder builder) {
        this.task = builder.task;
        this.description = builder.description;
        this.rewardXp = builder.rewardXp;
        this.rewardGold = builder.rewardGold;
        this.timeType = builder.timeType;
        this.completed = builder.completed;
        this.deadline = builder.deadline;
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

    public toDoTimeType getTimeType() {
        return timeType;
    }

    public void setTimeType(toDoTimeType timeType) {
        this.timeType = timeType;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadLine) {
        this.deadline = deadLine;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return completed == toDo.completed && Objects.equals(id, toDo.id) && Objects.equals(task, toDo.task) && Objects.equals(description, toDo.description) && Objects.equals(rewardXp, toDo.rewardXp) && Objects.equals(rewardGold, toDo.rewardGold) && timeType == toDo.timeType && Objects.equals(deadline, toDo.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, description, rewardXp, rewardGold, timeType, completed, deadline);
    }

    public void switchCompletionStatus() {
        this.completed = !this.completed;
    }

    public static class Builder {
        private final String task;
        private final Integer rewardXp = 100;
        private final Integer rewardGold = 50;
        private final boolean completed = false;
        private String description = "";
        private toDoTimeType timeType = toDoTimeType.NORMAL;
        private LocalDate deadline = LocalDate.now().plusDays(1);
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

        public Builder timeType(toDoTimeType timeType) {
            this.timeType = timeType;
            return this;
        }

        public Builder deadline(LocalDate deadline) {
            this.deadline = deadline;
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
