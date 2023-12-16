package com.mysite.sbb.Controller;

import com.mysite.sbb.DTO.LoginRequestDTO;
import com.mysite.sbb.DTO.SignupRequestDTO;
import com.mysite.sbb.DTO.SiteUserResponseDTO;
import com.mysite.sbb.Entity.SiteUser;
import com.mysite.sbb.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final LoginRequestDTO params) {
        Optional<SiteUser> siteUser = userService.findBy(params);

        if(siteUser.isPresent()) {
            SiteUserResponseDTO userDTO = new SiteUserResponseDTO();
            userDTO.setUsername(siteUser.get().getUsername());
            userDTO.setProfileImage(siteUser.get().getProfileImage());

            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody final SignupRequestDTO params) {
        if(!userService.existsByEmail(params.getEmail()) && !userService.existsByUsername(params.getUsername())) {
            SiteUser siteUser = userService.create(params.getUsername(), params.getEmail(), params.getPassword1());

            SiteUserResponseDTO userDTO = new SiteUserResponseDTO();
            userDTO.setUsername(siteUser.getUsername());

            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}