package com.workout_tracker.dto;

import java.time.LocalDateTime;

public class WorkoutResponse {
    private Long id;
    private LocalDateTime date;
    private Integer duration;
    private String type;
    private String notes;

    public WorkoutResponse(Long id, LocalDateTime date, Integer duration, String type, String notes) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.type = type;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
