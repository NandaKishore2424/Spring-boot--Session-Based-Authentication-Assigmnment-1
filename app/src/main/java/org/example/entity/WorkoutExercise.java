package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "workout_exercises")
public class WorkoutExercise {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workout_plan_id", nullable = false)
  private WorkoutPlan workoutPlan;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exercise_id", nullable = false)
  private Exercise exercise;

  @Column(name = "sets_count")
  private Integer setsCount;

  @Column(name = "reps_count")
  private Integer repsCount;

  @Column(name = "duration_minutes")
  private Integer durationMinutes;

  @Column(name = "rest_time_seconds")
  private Integer restTimeSeconds;

  @Column(name = "weight_kg")
  private Double weightKg;

  @Column(name = "distance_km")
  private Double distanceKm;

  @Column(name = "order_index")
  private Integer orderIndex;

  @Column(name = "notes", columnDefinition = "TEXT")
  private String notes;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public WorkoutExercise() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public WorkoutPlan getWorkoutPlan() {
    return workoutPlan;
  }

  public void setWorkoutPlan(WorkoutPlan workoutPlan) {
    this.workoutPlan = workoutPlan;
  }

  public Exercise getExercise() {
    return exercise;
  }

  public void setExercise(Exercise exercise) {
    this.exercise = exercise;
  }

  public Integer getSetsCount() {
    return setsCount;
  }

  public void setSetsCount(Integer setsCount) {
    this.setsCount = setsCount;
  }

  public Integer getRepsCount() {
    return repsCount;
  }

  public void setRepsCount(Integer repsCount) {
    this.repsCount = repsCount;
  }

  public Integer getDurationMinutes() {
    return durationMinutes;
  }

  public void setDurationMinutes(Integer durationMinutes) {
    this.durationMinutes = durationMinutes;
  }

  public Integer getRestTimeSeconds() {
    return restTimeSeconds;
  }

  public void setRestTimeSeconds(Integer restTimeSeconds) {
    this.restTimeSeconds = restTimeSeconds;
  }

  public Double getWeightKg() {
    return weightKg;
  }

  public void setWeightKg(Double weightKg) {
    this.weightKg = weightKg;
  }

  public Double getDistanceKm() {
    return distanceKm;
  }

  public void setDistanceKm(Double distanceKm) {
    this.distanceKm = distanceKm;
  }

  public Integer getOrderIndex() {
    return orderIndex;
  }

  public void setOrderIndex(Integer orderIndex) {
    this.orderIndex = orderIndex;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
