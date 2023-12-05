package com.mysite.sbb.DTO;

import com.mysite.sbb.Entity.SiteUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequestDTO {
    private String username;
    private String password1;
    private String password2;
    private String email;
    public SiteUser toEntity() {
        return SiteUser.builder()
                .username(username)
                .password(password1)
                .email(email)
                .build();
    }
}