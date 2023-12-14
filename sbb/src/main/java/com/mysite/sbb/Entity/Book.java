package com.mysite.sbb.Entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String author;

    private String publisher;

    private String image;

    private LocalDate publish_at;

    private String isbn;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<Room> roomList;

    @Builder
    public Book(String name, String author, String publisher, String image, String pubdate, String isbn) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.image = image;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        this.publish_at = LocalDate.parse(pubdate, formatter);
        this.isbn = isbn;
    }
}