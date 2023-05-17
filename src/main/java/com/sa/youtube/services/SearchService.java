package com.sa.youtube.services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.model.SearchResult;
import com.sa.youtube.clients.YoutubeClient;
import com.sa.youtube.dtos.VideoOutDTO;

@Service
public class SearchService {

    @Autowired
    YoutubeClient youtubeClient;

    public List<VideoOutDTO> search(String q)
        throws GeneralSecurityException, IOException, GoogleJsonResponseException {
            List<SearchResult> searchList = youtubeClient.getSearchList(q).getItems();
            return searchList.stream().map(VideoOutDTO::new).toList();
    }

}
