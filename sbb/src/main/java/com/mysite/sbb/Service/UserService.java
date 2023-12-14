package com.mysite.sbb.Service;

import com.mysite.sbb.DTO.LoginRequestDTO;
import com.mysite.sbb.DTO.SignupRequestDTO;
import com.mysite.sbb.Entity.SiteUser;
import com.mysite.sbb.Repository.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    public Optional<SiteUser> findBy(final LoginRequestDTO params) {
        return userRepository.findByUsernameAndPassword(params.getUsername(), params.getPassword());
    }

    public Optional<SiteUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public SiteUser create(String username, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreateDate(LocalDateTime.now());
        this.userRepository.save(user);
        return user;
    }
}