package com.workout_tracker.dto;

public class WorkoutStatsResponse {
    private Long totalWorkouts;
    private Integer totalDuration; // in minutes
    private Double averageDuration;

    public WorkoutStatsResponse(Long totalWorkouts, Integer totalDuration, Double averageDuration) {
        this.totalWorkouts = totalWorkouts;
        this.totalDuration = totalDuration;
        this.averageDuration = averageDuration;
    }

    public Long getTotalWorkouts() {
        return totalWorkouts;
    }

    public void setTotalWorkouts(Long totalWorkouts) {
        this.totalWorkouts = totalWorkouts;
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Double getAverageDuration() {
        return averageDuration;
    }

    public void setAverageDuration(Double averageDuration) {
        this.averageDuration = averageDuration;
    }
}
