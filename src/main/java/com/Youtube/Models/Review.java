package com.Youtube.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Float rating;
    private String videoID;

    private Boolean validateRating(Float rating) {
        return rating >=0 && rating <= 10;
    }
}
