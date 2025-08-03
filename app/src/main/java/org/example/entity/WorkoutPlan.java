package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "workout_plans")
public class WorkoutPlan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Plan name is required")
  @Size(max = 100)
  @Column(name = "plan_name")
  private String planName;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "difficulty_level")
  private DifficultyLevel difficultyLevel;

  @Column(name = "duration_weeks")
  private Integer durationWeeks;

  @Enumerated(EnumType.STRING)
  @Column(name = "goal_type")
  private GoalType goalType;

  @Column(name = "is_active")
  private Boolean isActive = true;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "trainer_id", nullable = false)
  private Trainer trainer;

  @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<WorkoutExercise> workoutExercises;

  public enum DifficultyLevel {
    BEGINNER, INTERMEDIATE, ADVANCED
  }

  public enum GoalType {
    WEIGHT_LOSS, MUSCLE_BUILDING, STRENGTH_TRAINING, CARDIO_FITNESS, FLEXIBILITY
  }

  public WorkoutPlan() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPlanName() {
    return planName;
  }

  public void setPlanName(String planName) {
    this.planName = planName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DifficultyLevel getDifficultyLevel() {
    return difficultyLevel;
  }

  public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
  }

  public Integer getDurationWeeks() {
    return durationWeeks;
  }

  public void setDurationWeeks(Integer durationWeeks) {
    this.durationWeeks = durationWeeks;
  }

  public GoalType getGoalType() {
    return goalType;
  }

  public void setGoalType(GoalType goalType) {
    this.goalType = goalType;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
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

  public Trainer getTrainer() {
    return trainer;
  }

  public void setTrainer(Trainer trainer) {
    this.trainer = trainer;
  }

  public List<WorkoutExercise> getWorkoutExercises() {
    return workoutExercises;
  }

  public void setWorkoutExercises(List<WorkoutExercise> workoutExercises) {
    this.workoutExercises = workoutExercises;
  }
}
