package com.mysite.sbb.Repository;

import com.mysite.sbb.Entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findByStatus(final int status, Pageable pageable);

}
