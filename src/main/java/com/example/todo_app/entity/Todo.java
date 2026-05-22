package com.example.todo_app.entity;

import jakarta.persistence.*;

@Entity
@Table(name="todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    boolean completed = false;

    protected Todo() {}

    public Todo(String title) {
        this.title = title;
    }

    // getter / setter

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
