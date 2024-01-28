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
    private long id;
    private String title;
    private long userId;
    private long imageId;
    private boolean blocked;
    private int likesNumber;
    private int commentsNumber;
}

