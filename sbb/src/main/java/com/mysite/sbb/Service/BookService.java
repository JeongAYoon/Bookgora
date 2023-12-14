package com.mysite.sbb.Service;

import com.mysite.sbb.Entity.Book;
import com.mysite.sbb.Repository.BookRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public Optional<Book> findByIsbn(String isbn) {
        Optional<Book> book = this.bookRepository.findByIsbn(isbn);

        return book;
    }

    public Book create(Book params) {
        Book book = new Book();

        book.setName(params.getName());
        book.setAuthor(params.getAuthor());
        book.setPublisher(params.getPublisher());
        book.setImage(params.getImage());
        book.setPublish_at(params.getPublish_at());
        book.setIsbn(params.getIsbn());
        this.bookRepository.save(book);

        return book;
    }
}
