package com.githubbuilder.github_builder_api.service;

import com.githubbuilder.github_builder_api.ProjectType;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ProjectDetectionService {
    public ProjectType detectProjectType(Path repoPath) {
        if (Files.exists(repoPath.resolve("pom.xml"))) {
            return ProjectType.MAVEN;
        }

        if (Files.exists(repoPath.resolve("build.gradle"))
            || Files.exists(repoPath.resolve("build.gradle.kts"))) {
            return ProjectType.GRADLE;
        }

        return ProjectType.UNKNOWN;
    }
}
