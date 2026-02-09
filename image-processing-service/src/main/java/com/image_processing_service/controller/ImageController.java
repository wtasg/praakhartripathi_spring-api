package com.image_processing_service.controller;

import com.image_processing_service.dto.ImageUploadResponse;
import com.image_processing_service.entity.ImageMetadata;
import com.image_processing_service.service.ImageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageUploadResponse> uploadImage(
        @RequestParam("file") MultipartFile file,
        @RequestParam(value = "width", required = false) Integer width,
        @RequestParam(value = "height", required = false) Integer height,
        @RequestParam(value = "quality", required = false) Float quality,
        @RequestParam(value = "format", required = false) String format
    ) {
        try {
            ImageUploadResponse response = imageService.processImage(file, width, height, quality, format);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/upload-from-url")
    public ResponseEntity<ImageUploadResponse> uploadImageFromUrl(@RequestBody Map<String, Object> payload) {
        try {
            String imageUrl = (String) payload.get("imageUrl");
            Integer width = payload.containsKey("width") ? (Integer) payload.get("width") : null;
            Integer height = payload.containsKey("height") ? (Integer) payload.get("height") : null;

            if (imageUrl == null || imageUrl.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            ImageUploadResponse response = imageService.processImageFromUrl(imageUrl, width, height);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{id}/convert")
    public ResponseEntity<ImageUploadResponse> convertImage(
        @PathVariable Long id,
        @RequestParam("format") String format
    ) {
        try {
            ImageUploadResponse response = imageService.convertImage(id, format);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageMetadata> getImageMetadata(@PathVariable Long id) {
        try {
            ImageMetadata metadata = imageService.getImageMetadata(id);
            return ResponseEntity.ok(metadata);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ImageMetadata>> getAllImages(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ImageMetadata> images = imageService.getAllImages(pageable);
        return ResponseEntity.ok(images);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteImage(@PathVariable Long id) {
        try {
            imageService.deleteImage(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "Image deleted successfully"));
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/thumbnail")
    public ResponseEntity<Map<String, String>> generateThumbnail(
        @PathVariable Long id,
        @RequestParam(defaultValue = "150") int width,
        @RequestParam(defaultValue = "150") int height
    ) {
        try {
            String thumbnailUrl = imageService.generateThumbnail(id, width, height);
            return ResponseEntity.ok(Collections.singletonMap("thumbnailUrl", thumbnailUrl));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
