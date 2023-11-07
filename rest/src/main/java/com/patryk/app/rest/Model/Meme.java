package com.patryk.app.rest.Model;

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
    private String name; 
    private String filePath;
}
