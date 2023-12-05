package com.mysite.sbb.Controller;

import java.util.List;

import com.mysite.sbb.Entity.Room;
import com.mysite.sbb.Service.RoomService;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/list")
    public List<Room> list() {
        List<Room> roomList = this.roomService.getList();
        return roomList;
    }

    @GetMapping(value = "/detail/{id}")
    public Room detail(@PathVariable("id") Integer id) {
        Room room = this.roomService.getRoom(id);
        return room;
    }
}