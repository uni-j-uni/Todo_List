package com.example.todo_list.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "User_Table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     // 기본키 Id를 입력하지 않아도 1부터 오름차순으로 자동 생성
    @Column(name="Users_Table_Id")
    private Long id;

    @Column(unique = true, nullable = false)      // Id 고유성(중복 불가)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String passwd;

    @Column(unique = true)      // Email 고유성(중복 불가)
    private String email;

    @OneToMany(mappedBy = "user")       // user는 여러 투두리스트를 가질 수 있음, 일대다 관계
    private List<Todo> todoList;
}
