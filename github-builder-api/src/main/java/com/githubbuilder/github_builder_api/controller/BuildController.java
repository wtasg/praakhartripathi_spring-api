package com.githubbuilder.github_builder_api.controller;

import com.githubbuilder.github_builder_api.ProjectType;
import com.githubbuilder.github_builder_api.dto.ArtifactDistributionResponse;
import com.githubbuilder.github_builder_api.dto.BuildRequest;
import com.githubbuilder.github_builder_api.service.ArtifactCopyService;
import com.githubbuilder.github_builder_api.service.BuildService;
import com.githubbuilder.github_builder_api.service.ProjectDetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/github")
public class BuildController {
    private final BuildService buildService;
    private final ProjectDetectionService detectionService;
    private final ArtifactCopyService copyService;

    public BuildController(BuildService buildService, ProjectDetectionService detectionService, ArtifactCopyService copyService) {
        this.buildService = buildService;
        this.detectionService = detectionService;
        this.copyService = copyService;
    }

    @PostMapping("/build")
    public ResponseEntity<ArtifactDistributionResponse> buildProject(@RequestBody BuildRequest request) {
        Path repoPath = Paths.get(request.getRepoPath());
        ProjectType projectType = detectionService.detectProjectType(repoPath);

        if (projectType != ProjectType.MAVEN) {
            throw new IllegalStateException("only maven project supported");
        }

        switch (projectType) {
            case MAVEN -> buildService.buildMavenProject(repoPath);
            case GRADLE -> buildService.buildGradleProject(repoPath);
            default -> throw new IllegalStateException("Unsupported project type");
        }
        Path jarFile = buildService.findJar(repoPath, projectType);

        String projectName = repoPath.getFileName().toString();

        Path internalPath = copyService.copyToInternal(jarFile, projectName);
        Path userPath = copyService.copyToUser(jarFile, request.getTargetPath());

        return ResponseEntity.ok(
            new ArtifactDistributionResponse(
                "SUCCESS",
                internalPath.toString(),
                userPath.toString()
            )
        );

    }
}
