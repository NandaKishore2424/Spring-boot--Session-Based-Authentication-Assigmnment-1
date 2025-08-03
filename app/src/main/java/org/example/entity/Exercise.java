package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Exercise name is required")
  @Size(max = 100)
  @Column(name = "exercise_name")
  private String exerciseName;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "category")
  private ExerciseCategory category;

  @Enumerated(EnumType.STRING)
  @Column(name = "equipment_type")
  private EquipmentType equipmentType;

  @Column(name = "muscle_groups")
  private String muscleGroups;

  @Column(name = "instructions", columnDefinition = "TEXT")
  private String instructions;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<WorkoutExercise> workoutExercises;

  public enum ExerciseCategory {
    STRENGTH, CARDIO, FLEXIBILITY, BALANCE, ENDURANCE
  }

  public enum EquipmentType {
    BODYWEIGHT, DUMBBELLS, BARBELL, MACHINE, RESISTANCE_BAND, CARDIO_EQUIPMENT, OTHER
  }

  public Exercise() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getExerciseName() {
    return exerciseName;
  }

  public void setExerciseName(String exerciseName) {
    this.exerciseName = exerciseName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ExerciseCategory getCategory() {
    return category;
  }

  public void setCategory(ExerciseCategory category) {
    this.category = category;
  }

  public EquipmentType getEquipmentType() {
    return equipmentType;
  }

  public void setEquipmentType(EquipmentType equipmentType) {
    this.equipmentType = equipmentType;
  }

  public String getMuscleGroups() {
    return muscleGroups;
  }

  public void setMuscleGroups(String muscleGroups) {
    this.muscleGroups = muscleGroups;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
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

  public List<WorkoutExercise> getWorkoutExercises() {
    return workoutExercises;
  }

  public void setWorkoutExercises(List<WorkoutExercise> workoutExercises) {
    this.workoutExercises = workoutExercises;
  }
}
