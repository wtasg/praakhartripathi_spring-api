package com.githubbuilder.github_builder_api.service;

import com.githubbuilder.github_builder_api.ProjectType;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BuildService {
    public void buildMavenProject(Path repo) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("mvn", "clean", "package", "-DskipTests");
            processBuilder.directory(repo.toFile());
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                while (reader.readLine() != null) {
                }
            }
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException("Maven build failed");
            }
        } catch (Exception e) {
            throw new RuntimeException("Build execution failed", e);
        }
    }

    public void buildGradleProject(Path repoPath) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("./gradlew", "build");

                processBuilder.directory(repoPath.toFile());
                processBuilder.redirectErrorStream(true);

                Process process = processBuilder.start();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    while(reader.readLine() != null) {}
                }
                int exitCode = process.waitFor();

                if(exitCode != 0) {
                    throw new RuntimeException("Gradle build failed");
                }
            } catch (Exception e) {
                throw new RuntimeException("Gradle build execution failed" , e);
            }
    }

    public Path getArtifactPath(Path repoPath) {
        return repoPath.resolve("target");
    }

    public Path findJar(Path repoPath, ProjectType projectType) {

        Path outputDir = switch (projectType) {
            case MAVEN -> repoPath.resolve("target");
            case GRADLE -> repoPath.resolve("build/libs");
            default -> throw new IllegalStateException("Unknown project type");
        };

        try {
            return Files.list(outputDir)
                    .filter(p -> p.toString().endsWith(".jar"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No JAR found"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to locate artifact", e);
        }
    }

}
