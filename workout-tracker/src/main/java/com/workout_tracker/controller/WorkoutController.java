package com.workout_tracker.controller;

import com.workout_tracker.dto.MessageResponse;
import com.workout_tracker.dto.WorkoutRequest;
import com.workout_tracker.dto.WorkoutResponse;
import com.workout_tracker.dto.WorkoutStatsResponse;
import com.workout_tracker.service.WorkoutService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<MessageResponse> createWorkout(@Valid @RequestBody WorkoutRequest workoutRequest) {
        return workoutService.createWorkout(workoutRequest);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponse>> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<WorkoutResponse> getWorkoutById(@PathVariable Long workoutId) {
        return workoutService.getWorkoutById(workoutId);
    }

    @PutMapping("/{workoutId}")
    public ResponseEntity<MessageResponse> updateWorkout(@PathVariable Long workoutId, @Valid @RequestBody WorkoutRequest workoutRequest) {
        return workoutService.updateWorkout(workoutId, workoutRequest);
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<MessageResponse> deleteWorkout(@PathVariable Long workoutId) {
        return workoutService.deleteWorkout(workoutId);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<WorkoutResponse>> getWorkoutsByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return workoutService.getWorkoutsByDate(date);
    }

    @GetMapping("/weekly")
    public ResponseEntity<List<WorkoutResponse>> getWeeklyWorkouts() {
        return workoutService.getWeeklyWorkouts();
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<WorkoutResponse>> getMonthlyWorkouts() {
        return workoutService.getMonthlyWorkouts();
    }

    @GetMapping("/stats")
    public ResponseEntity<WorkoutStatsResponse> getWorkoutStats() {
        return workoutService.getWorkoutStats();
    }

    @GetMapping("/export/csv")
    public void exportWorkoutsToCsv(HttpServletResponse response) throws IOException {
        workoutService.exportWorkoutsToCsv(response);
    }

    @GetMapping("/export/pdf")
    public void exportWorkoutsToPdf(HttpServletResponse response) throws IOException {
        workoutService.exportWorkoutsToPdf(response);
    }
}
