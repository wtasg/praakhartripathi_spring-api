package com.githubbuilder.github_builder_api.service;

import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GitCloneService {
    public Path cloneRepository(String githubUrl, String targetPath) {
        try {
            Path targetDir = Paths.get(targetPath);

            if (Files.exists((targetDir))) {
                throw new IllegalStateException("Target directory already exists " + targetPath);
            }
            Git.cloneRepository()
                .setURI(githubUrl)
                .setDirectory(targetDir.toFile())
                .call();

            return targetDir;

        } catch (Exception e) {
            throw new RuntimeException("failed to clone repository ", e);
        }
    }
}
