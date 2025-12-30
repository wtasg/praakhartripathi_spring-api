package com.task_tracker_api.service;

import com.task_tracker_api.dto.TaskRequest;
import com.task_tracker_api.dto.TaskUpdateRequest;
import com.task_tracker_api.entity.Task;
import com.task_tracker_api.entity.enums.TaskStatus;
import com.task_tracker_api.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        if (request.getTaskStatus() != null) {
            task.setStatus(request.getTaskStatus());
        } else {
            task.setStatus(TaskStatus.PENDING);
        }
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElse(null);

        if (task != null) {
            return task;
        } else {
            throw new RuntimeException("task not found with id" + id);
        }
    }

    @Override
    public Task updateTask(Long id, TaskUpdateRequest request) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (!optionalTask.isPresent()) {
            throw new RuntimeException("Task not found with id: " + id);
        }

        Task task = optionalTask.get();

        if(request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }

        if(request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }

        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if (!task.isPresent()) {
            throw new RuntimeException("task not found with id");
        }

        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTaskByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public Page<Task> getTasksWithPagination(int page, int size, String sortBy, String sortDir) {
        Sort sort;

        if (sortDir.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        return taskRepository.findAll(pageable);
    }
}
