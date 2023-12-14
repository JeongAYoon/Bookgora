package com.mysite.sbb.DTO;

import com.mysite.sbb.Entity.Book;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateBookRequestDTO {
    private String title;
    private String author;
    private String publisher;
    private String image;
    private String pubdate;
    private String isbn;

    public Book toEntity() {
        return Book.builder()
                .name(title)
                .author(author)
                .publisher(publisher)
                .image(image)
                .pubdate(pubdate)
                .isbn(isbn)
                .build();
    }
}
