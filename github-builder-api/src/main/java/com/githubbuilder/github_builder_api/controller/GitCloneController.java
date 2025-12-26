package com.githubbuilder.github_builder_api.controller;

import com.githubbuilder.github_builder_api.ProjectType;
import com.githubbuilder.github_builder_api.dto.CloneRequest;
import com.githubbuilder.github_builder_api.dto.CloneResponse;
import com.githubbuilder.github_builder_api.dto.DetectRequest;
import com.githubbuilder.github_builder_api.service.GitCloneService;
import com.githubbuilder.github_builder_api.service.ProjectDetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/github")
public class GitCloneController {
    private final GitCloneService gitCloneService;
    private final ProjectDetectionService detectionService;

    public GitCloneController(GitCloneService gitCloneService, ProjectDetectionService detectionService) {
        this.gitCloneService = gitCloneService;
        this.detectionService = detectionService;
    }

    @PostMapping("/clone")
    public ResponseEntity<CloneResponse> cloneRepo(
            @RequestBody CloneRequest request) {

        Path repoPath = gitCloneService.cloneRepository(
                request.getGithubUrl(),
                request.getTargetPath()
        );

        ProjectType projectType =
                detectionService.detectProjectType(repoPath);

        CloneResponse response = new CloneResponse(
                "Repository cloned successfully",
                projectType.name(),
                repoPath.toString()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/detect")
    public ResponseEntity<?> detectProject(@RequestBody DetectRequest request) {

        Path repoPath = Paths.get(request.getRepoPath());
        ProjectType projectType = detectionService.detectProjectType(repoPath);

        return ResponseEntity.ok(
                java.util.Map.of(
                        "projectType", projectType.name(),
                        "repoPath", repoPath.toString()
                )
        );
    }
}
