package com.mysite.sbb.Service;

import java.util.List;
import java.util.Optional;

import com.mysite.sbb.DataNotFoundException;

import com.mysite.sbb.Entity.Book;
import com.mysite.sbb.Entity.Room;
import com.mysite.sbb.Repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Room> getList(){
        return this.roomRepository.findAll();
    }

    public Page<Room> findByStatus(Pageable pageable){
        return roomRepository.findByStatus(1, pageable);
    }

    public Room create(Room params) {
        Room room = new Room();

        room.setSubject(params.getSubject());
        room.setBook(params.getBook());
        room.setBody(params.getBody());
        room.setCreator(params.getCreator());
        room.setStatus(params.getStatus());
        room.setCreateDate(params.getCreateDate());
        this.roomRepository.save(room);

        return room;
    }
}
