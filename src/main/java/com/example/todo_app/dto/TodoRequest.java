package com.example.todo_app.dto;

import jakarta.validation.constraints.NotBlank;

public record TodoRequest(
        @NotBlank(message = "제목은 비어있을 수 없습니다.")
        String title
) {
}
