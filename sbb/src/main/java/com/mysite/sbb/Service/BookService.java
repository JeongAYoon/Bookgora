package com.mysite.sbb.Service;

import com.mysite.sbb.Repository.BookRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

}
