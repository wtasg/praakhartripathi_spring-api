package com.workout_tracker.service.impl;

import com.workout_tracker.dto.ExerciseRequest;
import com.workout_tracker.dto.ExerciseResponse;
import com.workout_tracker.dto.MessageResponse;
import com.workout_tracker.entity.Exercise;
import com.workout_tracker.entity.User;
import com.workout_tracker.entity.Workout;
import com.workout_tracker.repository.ExerciseRepository;
import com.workout_tracker.repository.UserRepository;
import com.workout_tracker.repository.WorkoutRepository;
import com.workout_tracker.security.services.UserDetailsImpl;
import com.workout_tracker.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    UserRepository userRepository;

    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetailsImpl)) {
            return null;
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) principal;
        return userRepository.findById(userDetails.getId()).orElse(null);
    }

    private boolean isUserOwnerOfWorkout(User user, Workout workout) {
        return workout.getUser().getId().equals(user.getId());
    }

    @Override
    public ResponseEntity<MessageResponse> addExercise(Long workoutId, ExerciseRequest exerciseRequest) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not authenticated!"));
        }

        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if (workoutOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Workout not found!"));
        }

        Workout workout = workoutOptional.get();
        if (!isUserOwnerOfWorkout(user, workout)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Unauthorized access to workout!"));
        }

        Exercise exercise = new Exercise(
            exerciseRequest.getName(),
            exerciseRequest.getSets(),
            exerciseRequest.getReps(),
            exerciseRequest.getWeight(),
            workout
        );

        exerciseRepository.save(exercise);

        return ResponseEntity.ok(new MessageResponse("Exercise added successfully!"));
    }

    @Override
    public ResponseEntity<List<ExerciseResponse>> getAllExercisesByWorkoutId(Long workoutId) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if (workoutOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Workout workout = workoutOptional.get();
        if (!isUserOwnerOfWorkout(user, workout)) {
            return ResponseEntity.badRequest().build();
        }

        List<Exercise> exercises = exerciseRepository.findByWorkoutId(workoutId);
        List<ExerciseResponse> response = exercises.stream()
            .map(exercise -> new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getSets(),
                exercise.getReps(),
                exercise.getWeight()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ExerciseResponse> getExerciseById(Long workoutId, Long exerciseId) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if (workoutOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Workout workout = workoutOptional.get();
        if (!isUserOwnerOfWorkout(user, workout)) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        if (exerciseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Exercise exercise = exerciseOptional.get();
        if (!exercise.getWorkout().getId().equals(workoutId)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new ExerciseResponse(
            exercise.getId(),
            exercise.getName(),
            exercise.getSets(),
            exercise.getReps(),
            exercise.getWeight()
        ));
    }

    @Override
    public ResponseEntity<MessageResponse> updateExercise(Long workoutId, Long exerciseId, ExerciseRequest exerciseRequest) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not authenticated!"));
        }

        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if (workoutOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Workout not found!"));
        }

        Workout workout = workoutOptional.get();
        if (!isUserOwnerOfWorkout(user, workout)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Unauthorized access to workout!"));
        }

        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        if (exerciseOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Exercise not found!"));
        }

        Exercise exercise = exerciseOptional.get();
        if (!exercise.getWorkout().getId().equals(workoutId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Exercise does not belong to this workout!"));
        }

        exercise.setName(exerciseRequest.getName());
        exercise.setSets(exerciseRequest.getSets());
        exercise.setReps(exerciseRequest.getReps());
        exercise.setWeight(exerciseRequest.getWeight());

        exerciseRepository.save(exercise);

        return ResponseEntity.ok(new MessageResponse("Exercise updated successfully!"));
    }

    @Override
    public ResponseEntity<MessageResponse> deleteExercise(Long workoutId, Long exerciseId) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not authenticated!"));
        }

        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if (workoutOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Workout not found!"));
        }

        Workout workout = workoutOptional.get();
        if (!isUserOwnerOfWorkout(user, workout)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Unauthorized access to workout!"));
        }

        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        if (exerciseOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Exercise not found!"));
        }

        Exercise exercise = exerciseOptional.get();
        if (!exercise.getWorkout().getId().equals(workoutId)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Exercise does not belong to this workout!"));
        }

        exerciseRepository.delete(exercise);

        return ResponseEntity.ok(new MessageResponse("Exercise deleted successfully!"));
    }
}
