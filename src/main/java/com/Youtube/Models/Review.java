package com.Youtube.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review extends Message{
    private String id;
    private Float rating;
    private String videoID;

    private Boolean validateRating(Float rating) {
        return rating >=0 && rating <= 10;
    }
}
