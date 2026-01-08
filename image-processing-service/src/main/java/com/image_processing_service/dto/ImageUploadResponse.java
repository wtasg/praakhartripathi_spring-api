package com.image_processing_service.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ImageUploadResponse {
    private Long imageId;
    private String imageUrl;
    private long size;
    private String format;
    private LocalDateTime createdAt;
}
