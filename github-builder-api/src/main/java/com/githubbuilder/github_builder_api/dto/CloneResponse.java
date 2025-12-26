package com.githubbuilder.github_builder_api.dto;

public class CloneResponse {
    private String message;
    private String projectType;
    private String targetPath;

    public CloneResponse(String message, String projectType, String targetPath) {
        this.message = message;
        this.projectType = projectType;
        this.targetPath = targetPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
