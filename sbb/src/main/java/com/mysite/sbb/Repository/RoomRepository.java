package com.mysite.sbb.Repository;

import com.mysite.sbb.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
