package com.mysite.sbb.Entity;

import java.util.List;

import com.mysite.sbb.DTO.BookSearchResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NaverResult {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<BookSearchResponseDTO> items;
}