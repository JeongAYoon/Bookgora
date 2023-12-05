package com.mysite.sbb.Service;

import java.util.List;
import java.util.Optional;

import com.mysite.sbb.DataNotFoundException;

import com.mysite.sbb.Entity.Room;
import com.mysite.sbb.Repository.RoomRepository;
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
}
