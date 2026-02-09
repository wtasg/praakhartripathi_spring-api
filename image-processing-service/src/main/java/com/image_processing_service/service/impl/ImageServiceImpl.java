package com.image_processing_service.service.impl;

import com.image_processing_service.dto.ImageUploadResponse;
import com.image_processing_service.entity.ImageMetadata;
import com.image_processing_service.repository.ImageRepository;
import com.image_processing_service.service.ImageService;
import net.coobird.thumbnailator.Thumbnails;
import net.sf.image4j.codec.ico.ICOEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Value("${image.upload.dir:uploads}")
    private String uploadDir;

    @Value("${image.base.url:http://localhost:8080/images/}")
    private String baseUrl;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageUploadResponse processImage(MultipartFile file, Integer width, Integer height, Float quality, String format) throws IOException {
        // Create upload directory if not exists
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Determine output format
        String outputFormat = (format != null && !format.isEmpty()) ? format : getExtension(file.getOriginalFilename());
        if (outputFormat == null || outputFormat.isEmpty()) {
            outputFormat = "jpg"; // Default
        }

        // Generate unique filename
        String fileName = UUID.randomUUID().toString() + "." + outputFormat;
        File outputFile = new File(uploadDir, fileName);

        // Handle ICO format separately
        if ("ico".equalsIgnoreCase(outputFormat)) {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (width != null && height != null) {
                // Resize if needed before converting to ICO
                image = Thumbnails.of(image).size(width, height).asBufferedImage();
            }
            ICOEncoder.write(image, outputFile);
        } else {
            // Process image (Resize, Compress, Convert) using Thumbnailator
            Thumbnails.Builder<? extends InputStream> builder = Thumbnails.of(file.getInputStream());

            if (width != null && height != null) {
                builder.size(width, height);
            } else {
                builder.scale(1.0); // Maintain original size if not specified
            }

            if (quality != null) {
                builder.outputQuality(quality);
            }

            // With TwelveMonkeys, WebP, TIFF, BMP, etc. should be supported via ImageIO automatically
            builder.outputFormat(outputFormat);
            builder.toFile(outputFile);
        }

        // Save metadata to DB
        ImageMetadata metadata = new ImageMetadata();
        metadata.setUrl(baseUrl + fileName);
        metadata.setSize(outputFile.length());
        metadata.setFormat(outputFormat);

        ImageMetadata savedImage = imageRepository.save(metadata);

        return ImageUploadResponse.builder()
            .imageId(savedImage.getId())
            .imageUrl(savedImage.getUrl())
            .size(savedImage.getSize())
            .format(savedImage.getFormat())
            .createdAt(savedImage.getCreatedAt())
            .build();
    }

    @Override
    public ImageUploadResponse convertImage(Long imageId, String format) throws IOException {
        Optional<ImageMetadata> optionalMetadata = imageRepository.findById(imageId);
        if (optionalMetadata.isEmpty()) {
            throw new IOException("Image not found with id: " + imageId);
        }
        ImageMetadata imageMetadata = optionalMetadata.get();

        // Extract filename from URL
        String currentUrl = imageMetadata.getUrl();
        String currentFileName = currentUrl.substring(currentUrl.lastIndexOf("/") + 1);
        File inputFile = new File(uploadDir, currentFileName);

        if (!inputFile.exists()) {
            throw new IOException("Source file not found on server");
        }

        // Generate new filename
        String outputFormat = (format != null && !format.isEmpty()) ? format : "jpg";
        String newFileName = UUID.randomUUID().toString() + "." + outputFormat;
        File outputFile = new File(uploadDir, newFileName);

        if ("ico".equalsIgnoreCase(outputFormat)) {
            BufferedImage image = ImageIO.read(inputFile);
            // ICO usually requires small sizes, but we'll just convert as is or resize to 256x256 max if needed?
            // For now, just direct conversion.
            // Note: ICOEncoder might fail if image is too large.
            ICOEncoder.write(image, outputFile);
        } else {
            // Convert
            Thumbnails.of(inputFile)
                .scale(1.0)
                .outputFormat(outputFormat)
                .toFile(outputFile);
        }

        // Save new metadata
        ImageMetadata newMetadata = new ImageMetadata();
        newMetadata.setUrl(baseUrl + newFileName);
        newMetadata.setSize(outputFile.length());
        newMetadata.setFormat(outputFormat);

        ImageMetadata savedImage = imageRepository.save(newMetadata);

        return ImageUploadResponse.builder()
            .imageId(savedImage.getId())
            .imageUrl(savedImage.getUrl())
            .size(savedImage.getSize())
            .format(savedImage.getFormat())
            .createdAt(savedImage.getCreatedAt())
            .build();
    }

    @Override
    public ImageMetadata getImageMetadata(Long imageId) {
        Optional<ImageMetadata> optionalMetadata = imageRepository.findById(imageId);
        if (optionalMetadata.isPresent()) {
            return optionalMetadata.get();
        } else {
            throw new RuntimeException("Image not found with id: " + imageId);
        }
    }

    @Override
    public Page<ImageMetadata> getAllImages(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }

    @Override
    public void deleteImage(Long imageId) throws IOException {
        Optional<ImageMetadata> optionalMetadata = imageRepository.findById(imageId);
        if (optionalMetadata.isEmpty()) {
            throw new IOException("Image not found with id: " + imageId);
        }
        ImageMetadata imageMetadata = optionalMetadata.get();

        // Delete file from disk
        String currentUrl = imageMetadata.getUrl();
        String currentFileName = currentUrl.substring(currentUrl.lastIndexOf("/") + 1);
        File fileToDelete = new File(uploadDir, currentFileName);

        if (fileToDelete.exists()) {
            if (!fileToDelete.delete()) {
                // Log warning but continue to delete metadata
                System.err.println("Failed to delete file: " + fileToDelete.getAbsolutePath());
            }
        }

        // Delete metadata from DB
        imageRepository.delete(imageMetadata);
    }

    @Override
    public String generateThumbnail(Long imageId, int width, int height) throws IOException {
        Optional<ImageMetadata> optionalMetadata = imageRepository.findById(imageId);
        if (optionalMetadata.isEmpty()) {
            throw new IOException("Image not found with id: " + imageId);
        }
        ImageMetadata imageMetadata = optionalMetadata.get();

        // Source file
        String currentUrl = imageMetadata.getUrl();
        String currentFileName = currentUrl.substring(currentUrl.lastIndexOf("/") + 1);
        File inputFile = new File(uploadDir, currentFileName);

        if (!inputFile.exists()) {
            throw new IOException("Source file not found on server");
        }

        // Generate thumbnail filename
        String extension = getExtension(currentFileName);
        if (extension == null) extension = "jpg";

        String thumbFileName = imageId + "_" + width + "x" + height + "." + extension;
        File thumbFile = new File(uploadDir, thumbFileName);

        // Check if thumbnail already exists to avoid reprocessing
        if (thumbFile.exists()) {
            return baseUrl + thumbFileName;
        }

        // Generate thumbnail
        if ("ico".equalsIgnoreCase(extension)) {
            Thumbnails.of(inputFile)
                .size(width, height)
                .toFile(thumbFile);
        } else {
            Thumbnails.of(inputFile)
                .size(width, height)
                .toFile(thumbFile);
        }

        return baseUrl + thumbFileName;
    }

    @Override
    public ImageUploadResponse processImageFromUrl(String imageUrl, Integer width, Integer height) throws IOException {
        // Create upload directory if not exists
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Download file from URL
        URL url = new URL(imageUrl);
        String originalFileName = new File(url.getPath()).getName();
        String extension = getExtension(originalFileName);
        if (extension == null || extension.isEmpty()) {
            extension = "jpg"; // Default fallback
        }

        String fileName = UUID.randomUUID().toString() + "." + extension;
        File outputFile = new File(uploadDir, fileName);

        // Process image (Download + Resize if needed)
        Thumbnails.Builder<? extends URL> builder = Thumbnails.of(url);

        if (width != null && height != null) {
            builder.size(width, height);
        } else {
            builder.scale(1.0);
        }

        builder.toFile(outputFile);

        // Save metadata
        ImageMetadata metadata = new ImageMetadata();
        metadata.setUrl(baseUrl + fileName);
        metadata.setSize(outputFile.length());
        metadata.setFormat(extension);

        ImageMetadata savedImage = imageRepository.save(metadata);

        return ImageUploadResponse.builder()
            .imageId(savedImage.getId())
            .imageUrl(savedImage.getUrl())
            .size(savedImage.getSize())
            .format(savedImage.getFormat())
            .createdAt(savedImage.getCreatedAt())
            .build();
    }

    private String getExtension(String filename) {
        if (filename == null) return null;
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex >= 1) {
            return filename.substring(dotIndex + 1);
        }
        return null;
    }
}
