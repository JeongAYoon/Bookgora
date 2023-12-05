package com.mysite.sbb.Repository;

import com.mysite.sbb.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
