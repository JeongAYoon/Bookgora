package com.mysite.sbb.Entity;

import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32)
    private String subject;

    @ManyToOne
    private Book book;

    @Column(columnDefinition = "TEXT")
    private String body;

    @OneToOne
    @JoinColumn(name = "CREATOR")
    private SiteUser creator;

    @ManyToMany
    @JoinTable(name = "USER_PARTICIPANT")
    private List<SiteUser> participants = new ArrayList<>();

    private Integer userCount; // 현재 로그인 상태인 유저 수

    private Integer status; // 0 = disabled, 1 = enabled

    private LocalDateTime createDate;
}