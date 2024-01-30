package com.patryk.app.webapp.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String filePath;
    private long userId;
    private long memeId;

    public Image(String filePath, long userId, long memeId) {
        this.filePath = filePath;
        this.userId = userId;
        this.memeId = memeId;
    }
}
