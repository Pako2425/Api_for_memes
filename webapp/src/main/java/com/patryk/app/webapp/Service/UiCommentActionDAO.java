package com.patryk.app.webapp.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
public class UiCommentActionDAO {
    private long memeId;
    private long userId;
    private String url;
    private String commentContent;

    public UiCommentActionDAO(long memeId, long userId, String url, String commentContent) {
        this.memeId = memeId;
        this.userId = userId;
        this.url = url;
        this.commentContent = commentContent;
        System.out.println(commentContent);
        System.out.println(this.commentContent);
    }
}
