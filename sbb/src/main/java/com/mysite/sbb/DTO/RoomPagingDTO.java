package com.mysite.sbb.DTO;


import com.mysite.sbb.Entity.Room;
import com.mysite.sbb.Entity.Book;
import com.mysite.sbb.Entity.SiteUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomPagingDTO {
    private List<Room> rooms;
    private int totalPages;
    private int totalElements;
    private int currentPage;

}
