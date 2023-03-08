package com.Youtube.Models;

public class Review extends Message{
    private String id;
    private Float rating;
    private String videoID;

    private Boolean validateRating(Float rating) {
        return rating >=0 && rating <= 10;
    }
}
