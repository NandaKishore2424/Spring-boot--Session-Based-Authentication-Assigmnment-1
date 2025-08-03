package org.example.controller;

import org.example.dto.WorkoutPlanDTO;
import org.example.service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trainer/workout-plans")
@CrossOrigin(origins = "*")
public class WorkoutPlanController {

  @Autowired
  private WorkoutPlanService workoutPlanService;

  @PostMapping
  public ResponseEntity<WorkoutPlanDTO> createWorkoutPlan(@Valid @RequestBody WorkoutPlanDTO workoutPlanDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String trainerEmail = authentication.getName().replace("trainer:", "");
    WorkoutPlanDTO createdPlan = workoutPlanService.createWorkoutPlan(workoutPlanDTO, trainerEmail);
    return ResponseEntity.ok(createdPlan);
  }

  @PutMapping("/{planId}")
  public ResponseEntity<WorkoutPlanDTO> updateWorkoutPlan(@PathVariable Long planId,
      @Valid @RequestBody WorkoutPlanDTO workoutPlanDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String trainerEmail = authentication.getName().replace("trainer:", "");
    WorkoutPlanDTO updatedPlan = workoutPlanService.updateWorkoutPlan(planId, workoutPlanDTO, trainerEmail);
    return ResponseEntity.ok(updatedPlan);
  }

  @DeleteMapping("/{planId}")
  public ResponseEntity<String> deleteWorkoutPlan(@PathVariable Long planId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String trainerEmail = authentication.getName().replace("trainer:", "");
    workoutPlanService.deleteWorkoutPlan(planId, trainerEmail);
    return ResponseEntity.ok("Workout plan deleted successfully");
  }

  @GetMapping("/{planId}")
  public ResponseEntity<WorkoutPlanDTO> getWorkoutPlanById(@PathVariable Long planId) {
    WorkoutPlanDTO workoutPlan = workoutPlanService.getWorkoutPlanById(planId);
    return ResponseEntity.ok(workoutPlan);
  }

  @GetMapping("/my-plans")
  public ResponseEntity<List<WorkoutPlanDTO>> getMyWorkoutPlans() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String trainerEmail = authentication.getName().replace("trainer:", "");
    List<WorkoutPlanDTO> workoutPlans = workoutPlanService.getWorkoutPlansByTrainer(trainerEmail);
    return ResponseEntity.ok(workoutPlans);
  }

  @GetMapping("/public")
  public ResponseEntity<List<WorkoutPlanDTO>> getAllActiveWorkoutPlans() {
    List<WorkoutPlanDTO> workoutPlans = workoutPlanService.getAllActiveWorkoutPlans();
    return ResponseEntity.ok(workoutPlans);
  }
}
