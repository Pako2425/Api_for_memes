package com.patryk.app.webapp.Service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.sharing.SharedLinkSettings;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DropboxCommunicationService {
    private static final String ACCESS_TOKEN = "sl.Bs9TYwOsZX9z2rnnXtFBqvNGEgcyn99QtRK-g8vHlXxa2UM1v39ofzUzt08pDR358IsNXb3sc3sFgU5Pl4Zc7ZtD-Lq78O7bAUTRbhQoX-E9ueHkxDn9y6RI7QANket3_o8nX7k_T-6W8syWjXjFvTE";
    private DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    private DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

    public String saveImage(MultipartFile image, String path) throws IOException, DbxException {
        FileMetadata uploadedFile = client.files().uploadBuilder(path)
                .withMode(WriteMode.ADD)
                .uploadAndFinish(image.getInputStream());

        String link = client.files().getTemporaryLink(path).getLink();
        System.out.println("Link: " + link);
        return uploadedFile.getId();
    }

    public String getImagePath(String fileId) throws DbxException {
        Metadata metadata = client.files().getMetadata(fileId);
        if(metadata instanceof FileMetadata) {
            FileMetadata fileMetadata = (FileMetadata) metadata;
            return fileMetadata.getPathDisplay();
        }
        return null;
    }

}
