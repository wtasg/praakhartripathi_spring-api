package com.image_processing_service.repository;

import com.image_processing_service.entity.ImageMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageMetadata, Long> {
}
