package com.workout_tracker.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import com.workout_tracker.dto.MessageResponse;
import com.workout_tracker.dto.WorkoutRequest;
import com.workout_tracker.dto.WorkoutResponse;
import com.workout_tracker.dto.WorkoutStatsResponse;
import com.workout_tracker.entity.User;
import com.workout_tracker.entity.Workout;
import com.workout_tracker.repository.UserRepository;
import com.workout_tracker.repository.WorkoutRepository;
import com.workout_tracker.security.services.UserDetailsImpl;
import com.workout_tracker.service.WorkoutService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {

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

    @Override
    public ResponseEntity<MessageResponse> createWorkout(WorkoutRequest workoutRequest) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not authenticated!"));
        }

        Workout workout = new Workout(
            user,
            workoutRequest.getDate(),
            workoutRequest.getDuration(),
            workoutRequest.getType(),
            workoutRequest.getNotes()
        );

        workoutRepository.save(workout);

        return ResponseEntity.ok(new MessageResponse("Workout created successfully!"));
    }

    @Override
    public ResponseEntity<List<WorkoutResponse>> getAllWorkouts() {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Workout> workouts = workoutRepository.findByUserId(user.getId());
        List<WorkoutResponse> response = workouts.stream()
            .map(workout -> new WorkoutResponse(
                workout.getId(),
                workout.getDate(),
                workout.getDuration(),
                workout.getType(),
                workout.getNotes()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<WorkoutResponse> getWorkoutById(Long workoutId) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if (workoutOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Workout workout = workoutOptional.get();
        if (!workout.getUser().getId().equals(user.getId())) {
            return ResponseEntity.badRequest().build(); // Or unauthorized
        }

        return ResponseEntity.ok(new WorkoutResponse(
            workout.getId(),
            workout.getDate(),
            workout.getDuration(),
            workout.getType(),
            workout.getNotes()
        ));
    }

    @Override
    public ResponseEntity<MessageResponse> updateWorkout(Long workoutId, WorkoutRequest workoutRequest) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not authenticated!"));
        }

        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if (workoutOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Workout not found!"));
        }

        Workout workout = workoutOptional.get();
        if (!workout.getUser().getId().equals(user.getId())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Unauthorized access to workout!"));
        }

        workout.setDate(workoutRequest.getDate());
        workout.setDuration(workoutRequest.getDuration());
        workout.setType(workoutRequest.getType());
        workout.setNotes(workoutRequest.getNotes());

        workoutRepository.save(workout);

        return ResponseEntity.ok(new MessageResponse("Workout updated successfully!"));
    }

    @Override
    public ResponseEntity<MessageResponse> deleteWorkout(Long workoutId) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not authenticated!"));
        }

        Optional<Workout> workoutOptional = workoutRepository.findById(workoutId);
        if (workoutOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Workout not found!"));
        }

        Workout workout = workoutOptional.get();
        if (!workout.getUser().getId().equals(user.getId())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Unauthorized access to workout!"));
        }

        workoutRepository.delete(workout);

        return ResponseEntity.ok(new MessageResponse("Workout deleted successfully!"));
    }

    @Override
    public ResponseEntity<List<WorkoutResponse>> getWorkoutsByDate(LocalDate date) {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<Workout> workouts = workoutRepository.findByUserIdAndDateBetween(user.getId(), startOfDay, endOfDay);
        List<WorkoutResponse> response = workouts.stream()
            .map(workout -> new WorkoutResponse(
                workout.getId(),
                workout.getDate(),
                workout.getDuration(),
                workout.getType(),
                workout.getNotes()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<WorkoutResponse>> getWeeklyWorkouts() {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        LocalDate now = LocalDate.now();
        LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).atStartOfDay();
        LocalDateTime endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).atTime(LocalTime.MAX);

        List<Workout> workouts = workoutRepository.findByUserIdAndDateBetween(user.getId(), startOfWeek, endOfWeek);
        List<WorkoutResponse> response = workouts.stream()
            .map(workout -> new WorkoutResponse(
                workout.getId(),
                workout.getDate(),
                workout.getDuration(),
                workout.getType(),
                workout.getNotes()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<WorkoutResponse>> getMonthlyWorkouts() {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        LocalDate now = LocalDate.now();
        LocalDateTime startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
        LocalDateTime endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth()).atTime(LocalTime.MAX);

        List<Workout> workouts = workoutRepository.findByUserIdAndDateBetween(user.getId(), startOfMonth, endOfMonth);
        List<WorkoutResponse> response = workouts.stream()
            .map(workout -> new WorkoutResponse(
                workout.getId(),
                workout.getDate(),
                workout.getDuration(),
                workout.getType(),
                workout.getNotes()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<WorkoutStatsResponse> getWorkoutStats() {
        User user = getAuthenticatedUser();
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Workout> workouts = workoutRepository.findByUserId(user.getId());

        long totalWorkouts = workouts.size();
        int totalDuration = workouts.stream().mapToInt(Workout::getDuration).sum();
        double averageDuration = totalWorkouts > 0 ? (double) totalDuration / totalWorkouts : 0;

        return ResponseEntity.ok(new WorkoutStatsResponse(totalWorkouts, totalDuration, averageDuration));
    }

    @Override
    public void exportWorkoutsToCsv(HttpServletResponse response) throws IOException {
        User user = getAuthenticatedUser();
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
            return;
        }

        List<Workout> workouts = workoutRepository.findByUserId(user.getId());

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"workouts.csv\"");

        try (CSVWriter writer = new CSVWriter(response.getWriter())) {
            String[] header = {"ID", "Date", "Duration (min)", "Type", "Notes"};
            writer.writeNext(header);

            for (Workout workout : workouts) {
                String[] data = {
                    String.valueOf(workout.getId()),
                    workout.getDate().toString(),
                    String.valueOf(workout.getDuration()),
                    workout.getType(),
                    workout.getNotes()
                };
                writer.writeNext(data);
            }
        }
    }

    @Override
    public void exportWorkoutsToPdf(HttpServletResponse response) throws IOException {
        User user = getAuthenticatedUser();
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
            return;
        }

        List<Workout> workouts = workoutRepository.findByUserId(user.getId());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"workouts.pdf\"");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Paragraph chunk = new Paragraph("Workout History", font);
            chunk.setAlignment(Element.ALIGN_CENTER);
            document.add(chunk);
            document.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.addCell("ID");
            table.addCell("Date");
            table.addCell("Duration");
            table.addCell("Type");
            table.addCell("Notes");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            for (Workout workout : workouts) {
                table.addCell(String.valueOf(workout.getId()));
                table.addCell(workout.getDate().format(formatter));
                table.addCell(workout.getDuration() + " min");
                table.addCell(workout.getType());
                table.addCell(workout.getNotes() != null ? workout.getNotes() : "");
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new IOException("Error generating PDF", e);
        }
    }
}
