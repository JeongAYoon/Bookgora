package com.mysite.sbb.Controller;

import com.mysite.sbb.DTO.LoginRequestDTO;
import com.mysite.sbb.DTO.SignupRequestDTO;
import com.mysite.sbb.Entity.SiteUser;
import com.mysite.sbb.Service.UserService;
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
    public Optional<SiteUser> login(@RequestBody final LoginRequestDTO params) {
        return userService.findBy(params);
    }

    @PostMapping("/signup")
    public Optional<SiteUser> signup(@RequestBody final SignupRequestDTO params) {
        if(!userService.existsByEmail(params.getEmail()) && !userService.existsByUsername(params.getUsername())) {
            return Optional.of(userService.create(params.getUsername(), params.getEmail(), params.getPassword1()));
        } else {
            return Optional.empty();
        }
    }
}