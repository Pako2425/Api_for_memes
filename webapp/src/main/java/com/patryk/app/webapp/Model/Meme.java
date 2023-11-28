package com.patryk.app.webapp.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="memes")
public class Meme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String username;
    private long userId;
    private String filePath;
    private boolean blocked;
    private int likesNumber;
}
