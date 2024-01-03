package com.patryk.app.webapp.Service;

import com.dropbox.core.DbxException;
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
    private static final String FILE_SAVING_PATH = "/memes/";
    private final DropboxCommunicationService dropboxCommunicationService;

    public Meme processUploadedMemeData(UploadedMemeDAO uploadedMemeDAO) throws IOException, DbxException {
        System.out.println("in process method");
        Meme meme = new Meme();
        meme.setTitle(uploadedMemeDAO.getTitle());
        String filePath = FILE_SAVING_PATH + meme.getTitle() + ".jpg";
        meme.setFilePath(filePath);
        dropboxCommunicationService.saveImage(uploadedMemeDAO.getImage(), filePath);
        memesRepository.save(meme);
        return meme;
    }

    public void postMeme(UploadedMemeDAO uploadedMemeDAO) throws IOException, DbxException {

    }
}
