package org.example.dto;

public class WorkoutExerciseDTO {
  private Long id;
  private Long workoutPlanId;
  private Long exerciseId;
  private String exerciseName;
  private Integer setsCount;
  private Integer repsCount;
  private Integer durationMinutes;
  private Integer restTimeSeconds;
  private Double weightKg;
  private Double distanceKm;
  private Integer orderIndex;
  private String notes;

  public WorkoutExerciseDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getWorkoutPlanId() {
    return workoutPlanId;
  }

  public void setWorkoutPlanId(Long workoutPlanId) {
    this.workoutPlanId = workoutPlanId;
  }

  public Long getExerciseId() {
    return exerciseId;
  }

  public void setExerciseId(Long exerciseId) {
    this.exerciseId = exerciseId;
  }

  public String getExerciseName() {
    return exerciseName;
  }

  public void setExerciseName(String exerciseName) {
    this.exerciseName = exerciseName;
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
}
