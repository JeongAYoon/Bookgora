package com.mysite.sbb.DTO;

import lombok.Data;

@Data
public class BookResponseDTO {
    private String name;
    private String author;
    private String image;
    private String publisher;
}