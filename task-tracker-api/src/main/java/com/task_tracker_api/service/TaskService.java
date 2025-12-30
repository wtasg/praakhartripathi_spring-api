package com.task_tracker_api.service;

import com.task_tracker_api.dto.TaskRequest;
import com.task_tracker_api.entity.Task;

import java.util.List;

public interface TaskService {
    Task createTask(TaskRequest request);
    List<Task> getAllTask();
}
