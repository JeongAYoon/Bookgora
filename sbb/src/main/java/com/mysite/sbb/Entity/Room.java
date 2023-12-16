package com.mysite.sbb.Entity;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "CREATOR")
    private SiteUser creator;

    private Integer status; // 0 = disabled, 1 = enabled

    private LocalDateTime createDate;

    @Builder
    public Room(String subject, Book book, String body, SiteUser creator) {
        this.subject = subject;
        this.book = book;
        this.body = body;
        this.creator = creator;
        this.status = 1;
        this.createDate = LocalDateTime.now();
    }
}