package com.patryk.app.webapp.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long memeId;
    private long userId;
    private String content;
    private long parentCommentId;

    public Comment(long memeId, long userId, String content, long parentCommentId) {
        this.memeId = memeId;
        this.userId = userId;
        this.content = content;
        this.parentCommentId = parentCommentId;
    }
}
