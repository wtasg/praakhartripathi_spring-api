package com.githubbuilder.github_builder_api.dto;

public class DetectRequest {
    public String repoPath;

    public DetectRequest(String repoPath) {
        this.repoPath = repoPath;
    }

    public String getRepoPath() {
        return repoPath;
    }

    public void setRepoPath(String repoPath) {
        this.repoPath = repoPath;
    }
}
