package com.patryk.app.webapp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="images")
public class Image {
    private long id;
    private String filePath;
    private long userId;
    private long memeId;
}
