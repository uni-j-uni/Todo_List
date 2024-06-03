package com.example.todo_list.Controller;

import com.example.todo_list.Domain.Todo;
import com.example.todo_list.Dto.TodoDto;
import com.example.todo_list.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) { this.todoService = todoService; }

    // Create
    @PostMapping
    public ResponseEntity<Void> createTodo(@RequestBody TodoDto todoDto) {
        todoService.createTodo(todoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Read
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllUsers() {
        List<Todo> todos = todoService.getAllUsers();
        List<TodoDto> todoDtos = new ArrayList<>();
        for (Todo todo : todos) {
            TodoDto dto = TodoDto.from(todo);
            todoDtos.add(dto);
        }
        return ResponseEntity.ok(todoDtos);
    }

    @GetMapping("/{Userid}")
    public ResponseEntity<TodoDto> getUserById(@PathVariable(name="id") Long id) {
        Optional<Todo> todoOptional = todoService.getUserById(id);
        if (todoOptional.isPresent()) {
            TodoDto dto = TodoDto.from(todoOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable(name="id")Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoDto> UpdateMember(@PathVariable(name="id") Long id, @RequestBody TodoDto todoDto) {
        try {
            Todo updatedTodo = todoService.updateTodo(id, todoDto);
            return ResponseEntity.ok(TodoDto.from(updatedTodo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
