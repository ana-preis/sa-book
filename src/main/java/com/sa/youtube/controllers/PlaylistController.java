package com.sa.youtube.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.sa.youtube.clients.YoutubeClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// id exemplo PLntvgXM11X6pi7mW0O4ZmfUI1xDSIbmTm

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    
    @Autowired
    private YoutubeClient youtubeClient;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaylistById(@PathVariable String id)
        throws GeneralSecurityException, IOException {
            PlaylistListResponse response = youtubeClient.getPlaylist(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<?> getItemsById(@PathVariable String id)
        throws GeneralSecurityException, IOException {
            PlaylistItemListResponse response = youtubeClient.getPlaylistItems(id);
            return new ResponseEntity<>(response, HttpStatus.OK);

    }
    
}
