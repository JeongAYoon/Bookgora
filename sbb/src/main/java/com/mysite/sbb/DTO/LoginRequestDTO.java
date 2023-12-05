package com.mysite.sbb.DTO;

import com.mysite.sbb.Entity.SiteUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDTO {
    private String username;
    private String password;

    public SiteUser toEntity() {
        return SiteUser.builder()
                .username(username)
                .password(password)
                .build();
    }
}