package com.mysite.sbb.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomResponseDTO {
    private Long id;
    private String subject;
    private String body;
    private SiteUserResponseDTO creator;
    private LocalDateTime createDate;
    private BookResponseDTO book;
}