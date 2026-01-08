package com.image_processing_service.service;

import com.image_processing_service.dto.ImageUploadResponse;
import com.image_processing_service.entity.ImageMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageUploadResponse processImage(MultipartFile file, Integer width, Integer height, Float quality, String format) throws IOException;
    ImageUploadResponse convertImage(Long imageId, String format) throws IOException;
    ImageMetadata getImageMetadata(Long imageId);
    Page<ImageMetadata> getAllImages(Pageable pageable);
    void deleteImage(Long imageId) throws IOException;
    String generateThumbnail(Long imageId, int width, int height) throws IOException;
    ImageUploadResponse processImageFromUrl(String imageUrl, Integer width, Integer height) throws IOException;
}
