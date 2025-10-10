package com.example.habitrpg.model.entity;

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
    private boolean completed;
    private LocalDate deadline;

    public ToDo(String task, boolean completed, LocalDate deadline) {
        this.task = task;
        this.completed = completed;
        this.deadline = deadline;
    }

    public ToDo(String task) {
        this.task = task;
    }

    public ToDo(String task, LocalDate deadline) {
        this.task = task;
        this.completed = false;
        this.deadline = deadline;
    }

    public ToDo() {

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
        return completed == toDo.completed && Objects.equals(id, toDo.id) && Objects.equals(task, toDo.task) && Objects.equals(deadline, toDo.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, completed, deadline);
    }

    public void switchCompletionStatus(){
        this.completed = !this.completed;
    }
}
