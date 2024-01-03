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
    //private static final String FILE_SAVING_PATH = "D:/memes/";
    private static final String FILE_SAVING_PATH = "/memes";
    private final DropboxCommunicationService dropboxCommunicationService;

    public Meme processUploadedMemeData(UploadedMemeDAO uploadedMemeDAO) throws IOException, DbxException {
        System.out.println("in process method");
        Meme meme = new Meme();
        meme.setTitle(uploadedMemeDAO.getTitle());
        String filePath = FILE_SAVING_PATH + meme.getTitle() + ".jpg";
        meme.setFilePath(filePath);
        //uploadedMemeDAO.getImage().transferTo(new File(filePath));
        byte[] image = uploadedMemeDAO.getImage().getBytes();
        System.out.println("before dropbox service method");
        System.out.println("In service, call dropboxService");
        dropboxCommunicationService.saveImage(image, filePath);
        System.out.println("after dropbox service method");


        return meme;
    }

    //public Meme processUploadedMemeData(UploadedMemeDAO uploadedMemeDAO) throws IOException  {
    //    Meme meme = new Meme();
    //    meme.setTitle(uploadedMemeDAO.getTitle());
    //    String filePath = FILE_SAVING_PATH + meme.getTitle() + ".jpg";
    //    meme.setFilePath(filePath);
    //    uploadedMemeDAO.getImage().transferTo(new File(filePath));
    //
    //    return meme;
    //}

    public void postMeme(UploadedMemeDAO uploadedMemeDAO) throws IOException, DbxException {
        System.out.println("memesRepository save");
        memesRepository.save(processUploadedMemeData(uploadedMemeDAO));
        System.out.println("saved!");
    }
}
