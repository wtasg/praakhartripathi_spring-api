package com.workout_tracker.controller;

import com.workout_tracker.dto.ExerciseRequest;
import com.workout_tracker.dto.ExerciseResponse;
import com.workout_tracker.dto.MessageResponse;
import com.workout_tracker.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/workouts/{workoutId}/exercises")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<MessageResponse> addExercise(@PathVariable Long workoutId, @Valid @RequestBody ExerciseRequest exerciseRequest) {
        return exerciseService.addExercise(workoutId, exerciseRequest);
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> getAllExercisesByWorkoutId(@PathVariable Long workoutId) {
        return exerciseService.getAllExercisesByWorkoutId(workoutId);
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<ExerciseResponse> getExerciseById(@PathVariable Long workoutId, @PathVariable Long exerciseId) {
        return exerciseService.getExerciseById(workoutId, exerciseId);
    }

    @PutMapping("/{exerciseId}")
    public ResponseEntity<MessageResponse> updateExercise(@PathVariable Long workoutId, @PathVariable Long exerciseId, @Valid @RequestBody ExerciseRequest exerciseRequest) {
        return exerciseService.updateExercise(workoutId, exerciseId, exerciseRequest);
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<MessageResponse> deleteExercise(@PathVariable Long workoutId, @PathVariable Long exerciseId) {
        return exerciseService.deleteExercise(workoutId, exerciseId);
    }
}
