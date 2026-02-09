package com.workout_tracker.service;

import com.workout_tracker.dto.ExerciseRequest;
import com.workout_tracker.dto.ExerciseResponse;
import com.workout_tracker.dto.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExerciseService {
    ResponseEntity<MessageResponse> addExercise(Long workoutId, ExerciseRequest exerciseRequest);

    ResponseEntity<List<ExerciseResponse>> getAllExercisesByWorkoutId(Long workoutId);

    ResponseEntity<ExerciseResponse> getExerciseById(Long workoutId, Long exerciseId);

    ResponseEntity<MessageResponse> updateExercise(Long workoutId, Long exerciseId, ExerciseRequest exerciseRequest);

    ResponseEntity<MessageResponse> deleteExercise(Long workoutId, Long exerciseId);
}
