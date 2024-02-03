package com.patryk.app.webapp.Service;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UiFavoriteActionDAO {
    private long memeId;
    private long userId;
    private String url;

    UiFavoriteActionDAO(long memeId, long userId, String url) {
        this.memeId = memeId;
        this.userId = userId;
        this.url = url;
        System.out.println(memeId);
        System.out.println(userId);
        System.out.println(url);
    }
}
