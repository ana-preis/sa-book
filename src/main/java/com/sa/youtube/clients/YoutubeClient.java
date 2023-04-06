package com.sa.youtube.clients;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.google.api.services.youtube.model.VideoListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

@Service
public class YoutubeClient {
    @Value("${youtube.api.security.token.secret}")
    private static String DEVELOPER_KEY;

    private static final String APPLICATION_NAME = "sa-comunitube";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public void getVideoList() throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        System.out.println(DEVELOPER_KEY);
        YouTube youtubeService = getService();
        // Define and execute the API request
        YouTube.Videos.List request = youtubeService.videos()
                .list(Arrays.asList("snippet"));
        VideoListResponse response = request.setKey(DEVELOPER_KEY).setId(Arrays.asList("6EI1K4qP8YI")).execute();
        System.out.println(response);
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */

}