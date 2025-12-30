package com.task_tracker_api.dto;

import com.task_tracker_api.entity.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;

public class TaskRequest {
    @NotBlank(message = "title is required")
    private String title;
    private String description;
    private TaskStatus taskStatus;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
