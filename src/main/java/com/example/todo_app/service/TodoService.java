package com.example.todo_app.service;

import com.example.todo_app.entity.Todo;
import com.example.todo_app.repository.TodoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 전체 조회
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    // 단건 조회
    public Todo findById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo 를 찾을 수 없습니다."));
    }

    // 생성
    @Transactional
    public Todo create(String title) {
        Todo todo = new Todo(title);
        return todoRepository.save(todo);
    }

    // 제목 수정
    @Transactional
    public Todo updateTitle(Long id, String title) {
        Todo todo = findById(id);
        todo.setTitle(title);
        return todo;
    }

    // 완료 상태 토글
    @Transactional
    public Todo toggleCompleted(long  id) {
        Todo todo = findById(id);
        todo.setCompleted(!todo.isCompleted());
        return todo;
    }

    // 삭제
    @Transactional
    public void delete(Long id) {
        Todo todo = findById(id);
        todoRepository.delete(todo);
    }
}
