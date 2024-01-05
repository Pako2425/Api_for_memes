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
    private static final String DROPBOX_FILE_SAVING_PATH = "/memes/";
    private final DropboxCommunicationService dropboxCommunicationService;

    public Meme processUploadedMemeData(UploadedMemeDAO uploadedMemeDAO) throws IOException, DbxException {
        Meme meme = new Meme();
        meme.setTitle(uploadedMemeDAO.getTitle());
        //String filePath = FILE_SAVING_PATH + meme.getTitle() + ".jpg";
        String filePath = dropboxCommunicationService.saveImage(uploadedMemeDAO.getImage(), DROPBOX_FILE_SAVING_PATH);
        meme.setFilePath(filePath);
        memesRepository.save(meme);
        return meme;
    }

    public void postMeme(UploadedMemeDAO uploadedMemeDAO) throws IOException, DbxException {

    }
}
