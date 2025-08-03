package org.example.service;

import org.example.dto.WorkoutPlanDTO;
import org.example.entity.Trainer;
import org.example.entity.WorkoutPlan;
import org.example.repository.TrainerRepository;
import org.example.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutPlanService {

  @Autowired
  private WorkoutPlanRepository workoutPlanRepository;

  @Autowired
  private TrainerRepository trainerRepository;

  public WorkoutPlanDTO createWorkoutPlan(WorkoutPlanDTO workoutPlanDTO, String trainerEmail) {
    Trainer trainer = trainerRepository.findByEmail(trainerEmail)
        .orElseThrow(() -> new RuntimeException("Trainer not found"));

    WorkoutPlan workoutPlan = new WorkoutPlan();
    workoutPlan.setPlanName(workoutPlanDTO.getPlanName());
    workoutPlan.setDescription(workoutPlanDTO.getDescription());
    workoutPlan.setDifficultyLevel(WorkoutPlan.DifficultyLevel.valueOf(workoutPlanDTO.getDifficultyLevel()));
    workoutPlan.setDurationWeeks(workoutPlanDTO.getDurationWeeks());
    workoutPlan.setGoalType(WorkoutPlan.GoalType.valueOf(workoutPlanDTO.getGoalType()));
    workoutPlan.setIsActive(true);
    workoutPlan.setTrainer(trainer);
    workoutPlan.setCreatedAt(LocalDateTime.now());
    workoutPlan.setUpdatedAt(LocalDateTime.now());

    WorkoutPlan savedPlan = workoutPlanRepository.save(workoutPlan);
    return convertToDTO(savedPlan);
  }

  public WorkoutPlanDTO updateWorkoutPlan(Long planId, WorkoutPlanDTO workoutPlanDTO, String trainerEmail) {
    WorkoutPlan workoutPlan = workoutPlanRepository.findById(planId)
        .orElseThrow(() -> new RuntimeException("Workout plan not found"));

    if (!workoutPlan.getTrainer().getEmail().equals(trainerEmail)) {
      throw new RuntimeException("Unauthorized: You can only update your own workout plans");
    }

    workoutPlan.setPlanName(workoutPlanDTO.getPlanName());
    workoutPlan.setDescription(workoutPlanDTO.getDescription());
    workoutPlan.setDifficultyLevel(WorkoutPlan.DifficultyLevel.valueOf(workoutPlanDTO.getDifficultyLevel()));
    workoutPlan.setDurationWeeks(workoutPlanDTO.getDurationWeeks());
    workoutPlan.setGoalType(WorkoutPlan.GoalType.valueOf(workoutPlanDTO.getGoalType()));
    workoutPlan.setIsActive(workoutPlanDTO.getIsActive());
    workoutPlan.setUpdatedAt(LocalDateTime.now());

    WorkoutPlan savedPlan = workoutPlanRepository.save(workoutPlan);
    return convertToDTO(savedPlan);
  }

  public void deleteWorkoutPlan(Long planId, String trainerEmail) {
    WorkoutPlan workoutPlan = workoutPlanRepository.findById(planId)
        .orElseThrow(() -> new RuntimeException("Workout plan not found"));

    if (!workoutPlan.getTrainer().getEmail().equals(trainerEmail)) {
      throw new RuntimeException("Unauthorized: You can only delete your own workout plans");
    }

    workoutPlanRepository.delete(workoutPlan);
  }

  public WorkoutPlanDTO getWorkoutPlanById(Long planId) {
    WorkoutPlan workoutPlan = workoutPlanRepository.findById(planId)
        .orElseThrow(() -> new RuntimeException("Workout plan not found"));
    return convertToDTO(workoutPlan);
  }

  public List<WorkoutPlanDTO> getWorkoutPlansByTrainer(String trainerEmail) {
    Trainer trainer = trainerRepository.findByEmail(trainerEmail)
        .orElseThrow(() -> new RuntimeException("Trainer not found"));

    List<WorkoutPlan> workoutPlans = workoutPlanRepository.findByTrainerId(trainer.getId());
    return workoutPlans.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  public List<WorkoutPlanDTO> getAllActiveWorkoutPlans() {
    List<WorkoutPlan> workoutPlans = workoutPlanRepository.findAll()
        .stream()
        .filter(WorkoutPlan::getIsActive)
        .collect(Collectors.toList());
    return workoutPlans.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  private WorkoutPlanDTO convertToDTO(WorkoutPlan workoutPlan) {
    WorkoutPlanDTO dto = new WorkoutPlanDTO();
    dto.setId(workoutPlan.getId());
    dto.setPlanName(workoutPlan.getPlanName());
    dto.setDescription(workoutPlan.getDescription());
    dto.setDifficultyLevel(workoutPlan.getDifficultyLevel().toString());
    dto.setDurationWeeks(workoutPlan.getDurationWeeks());
    dto.setGoalType(workoutPlan.getGoalType().toString());
    dto.setIsActive(workoutPlan.getIsActive());
    dto.setTrainerId(workoutPlan.getTrainer().getId());
    dto.setTrainerName(workoutPlan.getTrainer().getFirstName() + " " + workoutPlan.getTrainer().getLastName());
    return dto;
  }
}
