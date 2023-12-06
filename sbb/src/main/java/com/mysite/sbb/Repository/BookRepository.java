package com.mysite.sbb.Repository;

import com.mysite.sbb.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByAuthor(String author);
    Optional<Book> findByName(String name);
}
