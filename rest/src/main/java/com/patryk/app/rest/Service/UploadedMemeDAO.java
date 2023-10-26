package com.patryk.app.rest.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
public class UploadedMemeDAO {
    private String title;
    private String username;
    private String filePath;
    private final MultipartFile meme;
}
