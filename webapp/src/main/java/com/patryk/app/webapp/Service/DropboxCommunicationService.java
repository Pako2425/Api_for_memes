package com.patryk.app.webapp.Service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.InvalidAccessTokenException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.sharing.SharedLinkSettings;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DropboxCommunicationService {
    private static String ACCESS_TOKEN = "sl.Bs8KqZbYYZCTUU1fHdPRYSYj9SdimOf1AMSLVWYYWLCu4GEitjVvP-drOM3xPw4QwtyVRZIR6nDNmCR7mZPtUyO-gMO43kXMaSiJYHYw2a2Hqw80samGMF72wV2fkQSl0gGriIf33m3Ed_o";
    //private DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    //private DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

    private String clientId = "pgur7x6tw12qoh1";
    private String clientSecret = "n3vbftnk5sfemym";
    private String tokenUrl = "https://api.dropbox.com/oauth2/token";
    private String refreshToken = "8kHwpCWZLIIAAAAAAAAAAcZGisOnvyKJkditmpnaDJgdJeZsmGYJpyCYBegXv2G3";
    DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();

    public String saveImage(MultipartFile image, String path) throws IOException, DbxException {
        try {
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
            System.out.println(ACCESS_TOKEN);
            FileMetadata uploadedFile = client.files().uploadBuilder(path)
                    .withMode(WriteMode.ADD)
                    .uploadAndFinish(image.getInputStream());
            String link = client.files().getTemporaryLink(path).getLink();
            System.out.println("Link: " + link);
            return uploadedFile.getId();
        } catch(InvalidAccessTokenException e) {
            ACCESS_TOKEN = generateAccessToken();
            System.out.println(ACCESS_TOKEN);
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
            FileMetadata uploadedFile = client.files().uploadBuilder(path)
                    .withMode(WriteMode.ADD)
                    .uploadAndFinish(image.getInputStream());
            String link = client.files().getTemporaryLink(path).getLink();
            System.out.println("Link: " + link);
        }
        return "";
    }

    public String getImagePath(String fileId) throws DbxException {
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        Metadata metadata = client.files().getMetadata(fileId);
        if(metadata instanceof FileMetadata) {
            FileMetadata fileMetadata = (FileMetadata) metadata;
            return fileMetadata.getPathDisplay();
        }
        return null;
    }

    public String generateAccessToken() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refreshToken);
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, request, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        if (response.getStatusCode().is2xxSuccessful()) {
            String newAccessToken = response.getBody();
            JsonNode jsonNode = objectMapper.readTree(newAccessToken);
            String accessToken = jsonNode.get("access_token").asText();
            System.out.println(accessToken);
            return accessToken;
        } else {
            System.out.println("null");
            return null;
        }
    }
}
