package com.example.todo_list.Dto;

import com.example.todo_list.Domain.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoDto {
    private Long id;
    private Long userId;            // NULL값을 가질 수 없음, 필수입력
    private String content;
    private Boolean completeStatus;
    private LocalDateTime createDate;

    public TodoDto() { this.completeStatus = false; }

    public static TodoDto from(Todo todo) {
        TodoDto todoDto = new TodoDto();

        todoDto.setId(todo.getId());
        todoDto.setUserId(todo.getUser().getId());
        todoDto.setContent(todo.getContent());
        todoDto.setCreateDate(todo.getCreateDate());
        todoDto.setCompleteStatus(todo.isCompleteStatus());

        return todoDto;
    }
}
