package com.sa.youtube.Infra;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class YoutubeClient {
    private static final String DEVELOPER_KEY = "AIzaSyDPNirVz5mmApgNaLnTRWUrYm_t0vWSqOs";

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

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */
    public static void main(String[] args)
            throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YouTube youtubeService = getService();
        List<String> playlistList = List.of("123456");
        // Define and execute the API request
        YouTube.Playlists.List request = youtubeService.playlists()
                .list(playlistList);
        PlaylistListResponse response = request.setKey(DEVELOPER_KEY)
                .setChannelId("12455")
                .execute();
        System.out.println(response);
    }
}