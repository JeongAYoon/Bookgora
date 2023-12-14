package com.mysite.sbb.DTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRoomRequestDTO {
    private String subject;
    private String isbn;
    private String body;
    private String username;
}
