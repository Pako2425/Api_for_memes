package com.patryk.app.webapp.Service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DropboxCommunicationService {
    private static final String ACCESS_TOKEN = "sl.Bs7yPDu08iVq4BcbCQSrop2rUaxbWvUE8VbxGHBwwRbpjNjQv80vleJoT0Es1alefb2ipYlHYxexkgOIqit7EyMWZ32Rey-FsXVO3YWOXrSDlFMaM-LAHf2_4WI5aw15CwB4VVlLgrVN1IhEl3WZxwk";
    private DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    private DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

    public void saveImage(byte[] image, String path) throws IOException, DbxException {
        System.out.println("before meme read");
        try (InputStream in = new FileInputStream("D:/memes/abc.jpg")) {
            System.out.println("after meme read");
            FileMetadata metadata = client.files().uploadBuilder("/memes/abc.jpg")
                    .uploadAndFinish(in);
            System.out.println("try upload");
            //client.files().uploadBuilder(path).uploadAndFinish(new ByteArrayInputStream(image));
        }
    }

    //public void saveMeme() throws IOException, DbxException {
    //    try (InputStream in = new FileInputStream("test.txt")) {
    //        FileMetadata metadata = client.files().uploadBuilder("/test.txt")
    //                .uploadAndFinish(in);
    //}

    //public void loadMeme(String name) {
    //
    //}
}
