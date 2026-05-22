package com.example.todo_app.controller;

import com.example.todo_app.dto.TodoRequest;
import com.example.todo_app.dto.TodoResponse;
import com.example.todo_app.entity.Todo;
import com.example.todo_app.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 전체 조회: GET /todos
    @GetMapping
    public List<TodoResponse> findAll() {
        return todoService.findAll().stream()
                .map(TodoResponse::from)
                .toList();
    }

    // 단건 조회: GET /todos/{id}
    public TodoResponse findById(@PathVariable Long id) {
        return TodoResponse.from(todoService.findById(id));
    }

    // 생성: POST /todos
    @PostMapping
    public ResponseEntity<TodoResponse> create(@Valid @RequestBody TodoRequest request) {
        Todo created = todoService.create(request.title());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TodoResponse.from(created));
    }

    // 제목 수정: PUT /todos/{id}
    @PutMapping("/{id}")
    public TodoResponse updateTitle(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
        return TodoResponse.from(todoService.updateTitle(id, request.title()));
    }

    // 완료 토글: PATCH /todos/{id}/toggle
    @PatchMapping("/{id}/toggle")
    public TodoResponse toggleCompleted(@PathVariable Long id) {
        return TodoResponse.from(todoService.toggleCompleted(id));
    }

    // 삭제: DELETE /todos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<TodoResponse> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
