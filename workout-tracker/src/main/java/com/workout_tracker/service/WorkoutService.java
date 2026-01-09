package com.workout_tracker.service;

import com.workout_tracker.dto.MessageResponse;
import com.workout_tracker.dto.WorkoutRequest;
import com.workout_tracker.dto.WorkoutResponse;
import com.workout_tracker.dto.WorkoutStatsResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface WorkoutService {
    ResponseEntity<MessageResponse> createWorkout(WorkoutRequest workoutRequest);
    ResponseEntity<List<WorkoutResponse>> getAllWorkouts();
    ResponseEntity<WorkoutResponse> getWorkoutById(Long workoutId);
    ResponseEntity<MessageResponse> updateWorkout(Long workoutId, WorkoutRequest workoutRequest);
    ResponseEntity<MessageResponse> deleteWorkout(Long workoutId);
    ResponseEntity<List<WorkoutResponse>> getWorkoutsByDate(LocalDate date);
    ResponseEntity<List<WorkoutResponse>> getWeeklyWorkouts();
    ResponseEntity<List<WorkoutResponse>> getMonthlyWorkouts();
    ResponseEntity<WorkoutStatsResponse> getWorkoutStats();
    void exportWorkoutsToCsv(HttpServletResponse response) throws IOException;
    void exportWorkoutsToPdf(HttpServletResponse response) throws IOException;
}
