package com.mysite.sbb.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private String publisher;

    private String image;

    private LocalDate publish_at;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<Room> roomList;
}