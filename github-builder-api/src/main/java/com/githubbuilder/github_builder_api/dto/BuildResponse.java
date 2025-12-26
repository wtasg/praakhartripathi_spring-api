package com.githubbuilder.github_builder_api.dto;

public class BuildResponse {
    private String status;
    private String artifactId;

    public BuildResponse(String status, String artifactId) {
        this.status = status;
        this.artifactId = artifactId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
}
