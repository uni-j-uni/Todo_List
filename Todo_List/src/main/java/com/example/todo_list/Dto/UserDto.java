package com.example.todo_list.Dto;

import com.example.todo_list.Domain.Todo;
import com.example.todo_list.Domain.User;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String userId;          // 중복 불가, NULL값 불가, 값이 없거나 중복시 오류 발생
    private String passwd;          // NULL값 불가
    private String name;            // NULL값 불가
    private String email;           // 중복 불가
    private List<Long> todoIds;

    public static UserDto from(User user) {
        UserDto userDto= new UserDto();
        userDto.setId(user.getId());
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setTodoIds(user.getTodoList().stream().map(Todo::getId).collect(Collectors.toList()));

        return userDto;
    }
}
