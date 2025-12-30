package com.task_tracker_api.controller;

import com.task_tracker_api.dto.TaskRequest;
import com.task_tracker_api.dto.TaskUpdateRequest;
import com.task_tracker_api.entity.Task;
import com.task_tracker_api.entity.enums.TaskStatus;
import com.task_tracker_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTask() {
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskUpdateRequest request) {
        Task updateTask = taskService.updateTask(id, request);
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("task deleted successfully");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(taskService.getTaskByStatus(status));
    }

    @GetMapping("/{page}")
    public ResponseEntity<Page<Task>> getTasksWithPagination(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(defaultValue = "createdAt") String sort,
                                                             @RequestParam(defaultValue = "desc") String direction) {
        Page<Task> tasks = taskService.getTasksWithPagination(page, size, sort, direction);
        return ResponseEntity.ok(tasks);
    }
}
