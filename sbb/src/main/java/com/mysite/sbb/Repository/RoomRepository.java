package com.mysite.sbb.Repository;

import com.mysite.sbb.Entity.Book;
import com.mysite.sbb.Entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findBySubject(final String subject);
    Optional<Room> findByBook(final Book book);

    Page<Room> findByStatus(final int status, Pageable pageable);
    Page<Room> findBySubjectContaining(final String subject, Pageable pageable);
    Page<Room> findByBookContaining(final Book book, Pageable pageable);

}
