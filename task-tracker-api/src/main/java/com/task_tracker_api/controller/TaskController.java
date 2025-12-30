package com.task_tracker_api.controller;

import com.task_tracker_api.dto.TaskRequest;
import com.task_tracker_api.entity.Task;
import com.task_tracker_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest request) {
        Task createdTask = taskService.createTask(request);
        return  new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
}
