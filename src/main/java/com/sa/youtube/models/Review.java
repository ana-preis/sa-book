package com.sa.youtube.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Review extends Message{
    private Float rating;
    @ManyToOne
    private Video video;
    private Boolean validateRating(Float rating) {
        return rating >=0 && rating <= 10;
    }
}
