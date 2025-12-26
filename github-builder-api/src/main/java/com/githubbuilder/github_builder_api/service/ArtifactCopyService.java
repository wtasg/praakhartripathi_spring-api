package com.githubbuilder.github_builder_api.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ArtifactCopyService {
    private static final Path INTERNAL_STORAGE = Paths.get("storage/builds");

    public Path copyToInternal(Path artifact, String projectName) {
        try {
            Path targetDir = INTERNAL_STORAGE.resolve(projectName);
            Files.createDirectories(targetDir);

            Path targetFile = targetDir.resolve(artifact.getFileName());
            Files.copy(artifact, targetFile, StandardCopyOption.REPLACE_EXISTING);

            return targetFile;
        } catch (IOException e) {
            throw new RuntimeException("failed to copy to internal storage" ,e);
        }
    }

    public Path copyToUser(Path artifact, String userPath) {
        try {
            Path targetDir = Paths.get(userPath);
            Files.createDirectories(targetDir);

            Path targetFile = targetDir.resolve(artifact.getFileName());
            Files.copy(artifact, targetFile, StandardCopyOption.REPLACE_EXISTING);

            return targetFile;

        } catch (IOException e) {
            throw new RuntimeException("Failed to copy to user location", e);
        }
    }
}
