package com.githubbuilder.github_builder_api.dto;

public class ArtifactDistributionResponse {
    private String status;
    private String internalPath;
    private String userPath;

    public ArtifactDistributionResponse(String status, String internalPath, String userPath) {
        this.status = status;
        this.internalPath = internalPath;
        this.userPath = userPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInternalPath() {
        return internalPath;
    }

    public void setInternalPath(String internalPath) {
        this.internalPath = internalPath;
    }

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath;
    }
}
