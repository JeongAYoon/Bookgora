package com.mysite.sbb.Controller;

import java.net.URI;
import java.util.Objects;

import java.util.List;
import java.util.Collections;

import com.mysite.sbb.DTO.BookSearchResponseDTO;
import com.mysite.sbb.Entity.NaverResult;
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


    @PostMapping ("/book/search")
    public List<BookSearchResponseDTO> searchBooks(@RequestBody String text) {
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
        ObjectMapper om = new ObjectMapper();
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
}