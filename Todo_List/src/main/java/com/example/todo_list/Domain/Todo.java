package com.example.todo_list.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     // 기본키 Id를 입력하지 않아도 1부터 오름차순으로 자동 생성
    @Column(name="Todo_Id")
    private Long id;

    @ManyToOne      // User_Id를 통한 다대일 조인
    @JoinColumn(name = "User_Id", nullable = false)     // user값이 NULL값을 가질 수 없음
    private User user;
    private boolean completeStatus;
    private String content;

    @CreationTimestamp
    private LocalDateTime createDate;
}
