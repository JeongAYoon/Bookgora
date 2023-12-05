package com.mysite.sbb.Entity;

import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.*;
import com.mysite.sbb.Entity.Room;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(unique = true)
    private String username;

    @Getter
    private String password;

    @Column(unique = true)
    private String email;

    @ColumnDefault("${default.image.address}")
    private String profileImage;

    @ManyToMany(mappedBy = "participants")
    private List<Room> rooms = new ArrayList<>();

    private LocalDateTime createDate;

    @Builder
    public SiteUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.rooms = new ArrayList<Room>();
        this.createDate = LocalDateTime.now();
    }
}