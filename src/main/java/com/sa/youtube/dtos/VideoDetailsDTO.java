package com.sa.youtube.dtos;

import java.util.List;

public record VideoDetailsDTO(
        VideoDTO videoDTO,
        List<ReviewDTO> reviewDTOs) {
}
