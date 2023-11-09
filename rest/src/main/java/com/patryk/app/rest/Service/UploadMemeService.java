package com.patryk.app.rest.Service;

import com.patryk.app.rest.Model.Meme;
import com.patryk.app.rest.Repository.MemesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@AllArgsConstructor
public class UploadMemeService {
    private final MemesRepository MEMES_REPOSITORY;

    public void postMeme(String name, MultipartFile image) throws IOException {
        Meme meme = new Meme();
        meme.setTitle(name);
        String filePath = "D:/memes/" + name + ".jpg";
        meme.setFilePath(filePath);

        MEMES_REPOSITORY.save(meme);
        image.transferTo(new File(filePath));
    }

}
