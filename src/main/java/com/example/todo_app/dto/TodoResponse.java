package com.example.todo_app.dto;

import com.example.todo_app.entity.Todo;

public record TodoResponse(
        Long id,
        String title,
        boolean completed
) {
    public static TodoResponse from(Todo todo) {
        return new TodoResponse(todo.getId(), todo.getTitle(), todo.isCompleted());
    }
}
