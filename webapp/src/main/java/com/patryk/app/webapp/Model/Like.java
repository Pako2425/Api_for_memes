package com.patryk.app.webapp.Model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@Table(name="likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long memeId;
    private long userId;

    public Like(long memeId, long userId) {
        this.memeId = memeId;
        this.userId = userId;
    }
}
