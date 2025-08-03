package org.example.service;

import org.example.dto.WorkoutExerciseDTO;
import org.example.entity.Exercise;
import org.example.entity.WorkoutExercise;
import org.example.entity.WorkoutPlan;
import org.example.repository.ExerciseRepository;
import org.example.repository.WorkoutExerciseRepository;
import org.example.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutExerciseService {

  @Autowired
  private WorkoutExerciseRepository workoutExerciseRepository;

  @Autowired
  private WorkoutPlanRepository workoutPlanRepository;

  @Autowired
  private ExerciseRepository exerciseRepository;

  public WorkoutExerciseDTO addExerciseToWorkoutPlan(WorkoutExerciseDTO workoutExerciseDTO, String trainerEmail) {
    WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutExerciseDTO.getWorkoutPlanId())
        .orElseThrow(() -> new RuntimeException("Workout plan not found"));

    if (!workoutPlan.getTrainer().getEmail().equals(trainerEmail)) {
      throw new RuntimeException("Unauthorized: You can only modify your own workout plans");
    }

    Exercise exercise = exerciseRepository.findById(workoutExerciseDTO.getExerciseId())
        .orElseThrow(() -> new RuntimeException("Exercise not found"));

    WorkoutExercise workoutExercise = new WorkoutExercise();
    workoutExercise.setWorkoutPlan(workoutPlan);
    workoutExercise.setExercise(exercise);
    workoutExercise.setSetsCount(workoutExerciseDTO.getSetsCount());
    workoutExercise.setRepsCount(workoutExerciseDTO.getRepsCount());
    workoutExercise.setDurationMinutes(workoutExerciseDTO.getDurationMinutes());
    workoutExercise.setRestTimeSeconds(workoutExerciseDTO.getRestTimeSeconds());
    workoutExercise.setWeightKg(workoutExerciseDTO.getWeightKg());
    workoutExercise.setDistanceKm(workoutExerciseDTO.getDistanceKm());
    workoutExercise.setOrderIndex(workoutExerciseDTO.getOrderIndex());
    workoutExercise.setNotes(workoutExerciseDTO.getNotes());
    workoutExercise.setCreatedAt(LocalDateTime.now());
    workoutExercise.setUpdatedAt(LocalDateTime.now());

    WorkoutExercise savedWorkoutExercise = workoutExerciseRepository.save(workoutExercise);
    return convertToDTO(savedWorkoutExercise);
  }

  public WorkoutExerciseDTO updateWorkoutExercise(Long workoutExerciseId, WorkoutExerciseDTO workoutExerciseDTO,
      String trainerEmail) {
    WorkoutExercise workoutExercise = workoutExerciseRepository.findById(workoutExerciseId)
        .orElseThrow(() -> new RuntimeException("Workout exercise not found"));

    if (!workoutExercise.getWorkoutPlan().getTrainer().getEmail().equals(trainerEmail)) {
      throw new RuntimeException("Unauthorized: You can only modify your own workout plans");
    }

    workoutExercise.setSetsCount(workoutExerciseDTO.getSetsCount());
    workoutExercise.setRepsCount(workoutExerciseDTO.getRepsCount());
    workoutExercise.setDurationMinutes(workoutExerciseDTO.getDurationMinutes());
    workoutExercise.setRestTimeSeconds(workoutExerciseDTO.getRestTimeSeconds());
    workoutExercise.setWeightKg(workoutExerciseDTO.getWeightKg());
    workoutExercise.setDistanceKm(workoutExerciseDTO.getDistanceKm());
    workoutExercise.setOrderIndex(workoutExerciseDTO.getOrderIndex());
    workoutExercise.setNotes(workoutExerciseDTO.getNotes());
    workoutExercise.setUpdatedAt(LocalDateTime.now());

    WorkoutExercise savedWorkoutExercise = workoutExerciseRepository.save(workoutExercise);
    return convertToDTO(savedWorkoutExercise);
  }

  public void removeExerciseFromWorkoutPlan(Long workoutExerciseId, String trainerEmail) {
    WorkoutExercise workoutExercise = workoutExerciseRepository.findById(workoutExerciseId)
        .orElseThrow(() -> new RuntimeException("Workout exercise not found"));

    if (!workoutExercise.getWorkoutPlan().getTrainer().getEmail().equals(trainerEmail)) {
      throw new RuntimeException("Unauthorized: You can only modify your own workout plans");
    }

    workoutExerciseRepository.delete(workoutExercise);
  }

  public List<WorkoutExerciseDTO> getExercisesInWorkoutPlan(Long workoutPlanId) {
    List<WorkoutExercise> workoutExercises = workoutExerciseRepository
        .findByWorkoutPlanIdOrderByOrderIndex(workoutPlanId);
    return workoutExercises.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  @Transactional
  public void removeAllExercisesFromWorkoutPlan(Long workoutPlanId, String trainerEmail) {
    WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId)
        .orElseThrow(() -> new RuntimeException("Workout plan not found"));

    if (!workoutPlan.getTrainer().getEmail().equals(trainerEmail)) {
      throw new RuntimeException("Unauthorized: You can only modify your own workout plans");
    }

    workoutExerciseRepository.deleteByWorkoutPlanId(workoutPlanId);
  }

  private WorkoutExerciseDTO convertToDTO(WorkoutExercise workoutExercise) {
    WorkoutExerciseDTO dto = new WorkoutExerciseDTO();
    dto.setId(workoutExercise.getId());
    dto.setWorkoutPlanId(workoutExercise.getWorkoutPlan().getId());
    dto.setExerciseId(workoutExercise.getExercise().getId());
    dto.setExerciseName(workoutExercise.getExercise().getExerciseName());
    dto.setSetsCount(workoutExercise.getSetsCount());
    dto.setRepsCount(workoutExercise.getRepsCount());
    dto.setDurationMinutes(workoutExercise.getDurationMinutes());
    dto.setRestTimeSeconds(workoutExercise.getRestTimeSeconds());
    dto.setWeightKg(workoutExercise.getWeightKg());
    dto.setDistanceKm(workoutExercise.getDistanceKm());
    dto.setOrderIndex(workoutExercise.getOrderIndex());
    dto.setNotes(workoutExercise.getNotes());
    return dto;
  }
}
