package com.sa.youtube.clients;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.VideoListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Service
public class YoutubeClient {

    @Value("${youtube.api.security.token.secret}")
    private String DEVELOPER_KEY;
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

    public VideoListResponse getVideo(String id) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YouTube youtubeService = getService();
        // Define and execute the API request
        YouTube.Videos.List request = youtubeService.videos()
                .list(Arrays.asList("snippet", "statistics", "player"));
        VideoListResponse response = request.setId(Arrays.asList(id))
            .setKey(DEVELOPER_KEY)
            .execute();
        return response;
    }

    public PlaylistListResponse getPlaylist(String id) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YouTube youtubeService = getService();
        YouTube.Playlists.List request = youtubeService.playlists()
            .list(Arrays.asList("snippet", "contentDetails"));
        PlaylistListResponse response = request.setId(Arrays.asList(id))
            .setKey(DEVELOPER_KEY)
            .execute();
        return response;
    }

    public PlaylistItemListResponse getPlaylistItems(String id) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YouTube youtubeService = getService();
        YouTube.PlaylistItems.List request = youtubeService.playlistItems()
            .list(Arrays.asList("snippet", "contentDetails"));
        PlaylistItemListResponse response = request.setPlaylistId(id)
            .setKey(DEVELOPER_KEY)
            .execute();
        return response;
    }

    public SearchListResponse getSearchList(String keyword, String type) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YouTube youtubeService = getService();
        // Define and execute the API request
        YouTube.Search.List request = youtubeService.search()
            .list(Arrays.asList("snippet"));
        SearchListResponse response = request.setMaxResults(25L).setQ(keyword).setType(Arrays.asList(type))
            .setKey(DEVELOPER_KEY)
            .execute();
        return response;
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */

}