package com.task_tracker_api.service;

import com.task_tracker_api.dto.TaskRequest;
import com.task_tracker_api.dto.TaskUpdateRequest;
import com.task_tracker_api.entity.Task;
import com.task_tracker_api.entity.enums.TaskStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    Task createTask(TaskRequest request);

    List<Task> getAllTask();

    Task getTaskById(Long id);

    Task updateTask(Long id, TaskUpdateRequest request);

    void deleteTask(Long id);

    List<Task> getTaskByStatus(TaskStatus taskStatus);

    Page<Task> getTasksWithPagination(int page, int size, String sortBy, String sortDir);
}
