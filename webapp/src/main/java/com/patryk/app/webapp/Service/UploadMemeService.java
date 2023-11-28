package com.patryk.app.webapp.Service;

import com.patryk.app.webapp.Model.Meme;
import com.patryk.app.webapp.Repository.MemesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

@Service
@AllArgsConstructor
public class UploadMemeService {
    private final MemesRepository memesRepository;
    private static final String FILE_SAVING_PATH = "D:/memes/";

    public Meme processUploadedMemeData(UploadedMemeDAO uploadedMemeDAO) throws IOException  {
        Meme meme = new Meme();
        meme.setTitle(uploadedMemeDAO.getTitle());
        String filePath = FILE_SAVING_PATH + meme.getTitle() + ".jpg";
        meme.setFilePath(filePath);
        uploadedMemeDAO.getImage().transferTo(new File(filePath));

        return meme;
    }

    public void postMeme(UploadedMemeDAO uploadedMemeDAO) throws IOException {

        memesRepository.save(processUploadedMemeData(uploadedMemeDAO));
    }
}
