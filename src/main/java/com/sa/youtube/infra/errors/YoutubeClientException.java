package com.sa.youtube.infra.errors;

public class YoutubeClientException extends RuntimeException {
    public YoutubeClientException(String message) {
        super(message);
    }
}
