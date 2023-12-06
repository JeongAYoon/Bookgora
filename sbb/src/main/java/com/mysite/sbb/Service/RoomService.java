package com.mysite.sbb.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mysite.sbb.DTO.LoginRequestDTO;
import com.mysite.sbb.DTO.RoomPagingDTO;
import com.mysite.sbb.DataNotFoundException;

import com.mysite.sbb.Entity.Book;
import com.mysite.sbb.Entity.Room;
import com.mysite.sbb.Entity.SiteUser;
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

    public Room getRoom(Integer id){
        Optional<Room> room = this.roomRepository.findById(id);
        if (room.isPresent()){
            return room.get();
        } else{
            throw new DataNotFoundException("room not found");
        }
    }

    public Page<Room> findByStatus(Pageable pageable){
        return roomRepository.findByStatus(1, pageable);
    }

    public Page<Room> findBySubjectContaining(String subject, Pageable pageable){
        return roomRepository.findBySubjectContaining(subject, pageable);
    }

    public Page<Room> findByBookContaining(Book book, Pageable pageable){
        return roomRepository.findByBookContaining(book, pageable);
    }
/*
    public RoomPagingDTO getRoomsPaged(Pageable pageable){
        Page<Room> roomPage = roomRepository.findAll(pageable);
        List<Room> rooms = roomPage.getContent().stream()


        return new RoomPagingDTO(rooms, roomPage.getTotalPages(), roomPage.getTotalElements());
    }
*/
}
