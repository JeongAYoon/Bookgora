package com.mysite.sbb.Controller;

import java.net.URI;
import java.util.Objects;

import java.util.List;
import java.util.Collections;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.mysite.sbb.DTO.BookSearchResponseDTO;
import com.mysite.sbb.DTO.CreateBookRequestDTO;
import com.mysite.sbb.Entity.Book;
import com.mysite.sbb.Entity.NaverResult;
import com.mysite.sbb.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BookController {
    private final BookService bookService;

    @PostMapping ("/book/search")
    public List<BookSearchResponseDTO> searchBooks(@RequestBody String requestBody) {
        ObjectMapper om = new ObjectMapper();
        String text;

        try {
            JsonNode jsonNode = om.readTree(requestBody);
            text = jsonNode.get("text").asText();
        } catch (JsonProcessingException | NullPointerException e) {
            String errorMessage = "JSON 파싱 중 에러 발생: " + e.getMessage();
            throw new RuntimeException(errorMessage);
        }

        String clientId = "MnsxtlHwxcz5MqWLRHq_";
        String clientSecret = "JfV2UfZJmT";

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", text)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();
        NaverResult result;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> rest;
        rest=restTemplate.exchange(req, String.class);
        try {
            result = om.readValue(rest.getBody(), NaverResult.class);
        } catch (JsonProcessingException e) {
            String errorMessage = "JSON 파싱 중 에러 발생: " + e.getMessage();
            throw new RuntimeException(errorMessage);
        }

        if (result != null) {
            return Objects.requireNonNull(result).getItems();
        } else {
            return Collections.emptyList();
        }
    }

    @PostMapping("/book/create")
    public ResponseEntity<?> createBook(@RequestBody final CreateBookRequestDTO params) {
        if(this.bookService.findByIsbn(params.getIsbn()).isPresent())
        {
            Book book = this.bookService.findByIsbn(params.getIsbn()).get();

            return ResponseEntity.ok().build();
        } else {
            Book book = this.bookService.create(params.toEntity());

            return ResponseEntity.ok().build();
        }
    }
}