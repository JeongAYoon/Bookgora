package com.mysite.sbb.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.mysite.sbb.DTO.RoomPagingDTO;
import com.mysite.sbb.Entity.Room;
import com.mysite.sbb.Service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/list")
    public String List(@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 9);
        Page<Room> roomPage = null;
        List<Room> roomList = this.roomService.getList();
        roomPage  = roomService.findByStatus(pageable);

        List<Object> list = new ArrayList<>();
        Gson gson = new Gson();
        String pageGson = gson.toJson(roomPage);
        return pageGson;
    }
}