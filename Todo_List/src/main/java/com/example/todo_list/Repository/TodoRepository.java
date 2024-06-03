package com.example.todo_list.Repository;

import com.example.todo_list.Domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long UserId);       // userId를 통하여 검색할 수 있도록
}
