package com.mysite.sbb.Controller;

import com.mysite.sbb.DTO.CreateRoomRequestDTO;
import com.mysite.sbb.Entity.Book;
import com.mysite.sbb.Entity.Room;
import com.mysite.sbb.Entity.SiteUser;
import com.mysite.sbb.Service.BookService;
import com.mysite.sbb.Service.RoomService;
import com.mysite.sbb.Service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService roomService;
    private final BookService bookService;
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<Page<Room>> listRooms(@RequestParam(defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 9);
        Page<Room> roomPage = roomService.findByStatus(pageable);
        return ResponseEntity.ok(roomPage);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomRequestDTO params) {
        String isbn = params.getIsbn();
        String username = params.getUsername();

        Optional<Book> book = bookService.findByIsbn(isbn);
        Optional<SiteUser> user = userService.findByUsername(username);

        Room newRoom = Room.builder()
                .subject(params.getSubject())
                .book(book.get())
                .body(params.getBody())
                .creator(user.get())
                .build();

        Room savedRoom = roomService.create(newRoom);
        if (savedRoom != null) {
            return ResponseEntity.ok(savedRoom);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create room.");
        }
    }
}