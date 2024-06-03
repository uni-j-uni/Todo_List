package com.example.todo_list.Service;

import com.example.todo_list.Domain.Todo;
import com.example.todo_list.Domain.User;
import com.example.todo_list.Dto.TodoDto;
import com.example.todo_list.Repository.TodoRepository;
import com.example.todo_list.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    // Create
    @Transactional
    public void createTodo(TodoDto todoDto) {
        User user = userRepository.findById(todoDto.getUserId()).orElse(null);
        if (user == null) return;
        Todo todo = new Todo();
        todo.setUser(user);
        todo.setContent(todoDto.getContent());
        todo.setCompleteStatus(todoDto.getCompleteStatus());
        todoRepository.save(todo);
    }

    // Read
    public List<Todo> getAllUsers() { return todoRepository.findAll(); }
    public Optional<Todo> getUserById(Long id) { return todoRepository.findById(id); }

    // Update
    @Transactional
    public Todo updateTodo(Long id, TodoDto todoDto) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            if (todoDto.getId() != null) todo.setId(todoDto.getId());
            if (todoDto.getContent() != null) todo.setContent(todoDto.getContent());
            if (todoDto.getCompleteStatus() != null) todo.setCompleteStatus(todoDto.getCompleteStatus());

            return todoRepository.save(todo);
        }
        else throw new RuntimeException("Todo not found with id " + id);
    }

    // Delete
    @Transactional
    public void deleteTodo(Long id) { todoRepository.deleteById(id); }
}
