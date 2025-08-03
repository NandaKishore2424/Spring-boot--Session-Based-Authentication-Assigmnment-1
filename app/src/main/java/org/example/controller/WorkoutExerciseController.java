package org.example.controller;

import org.example.dto.WorkoutExerciseDTO;
import org.example.service.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trainer/workout-exercises")
@CrossOrigin(origins = "*")
public class WorkoutExerciseController {

  @Autowired
  private WorkoutExerciseService workoutExerciseService;

  @PostMapping
  public ResponseEntity<WorkoutExerciseDTO> addExerciseToWorkoutPlan(
      @Valid @RequestBody WorkoutExerciseDTO workoutExerciseDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String trainerEmail = authentication.getName().replace("trainer:", "");
    WorkoutExerciseDTO createdWorkoutExercise = workoutExerciseService.addExerciseToWorkoutPlan(workoutExerciseDTO,
        trainerEmail);
    return ResponseEntity.ok(createdWorkoutExercise);
  }

  @PutMapping("/{workoutExerciseId}")
  public ResponseEntity<WorkoutExerciseDTO> updateWorkoutExercise(@PathVariable Long workoutExerciseId,
      @Valid @RequestBody WorkoutExerciseDTO workoutExerciseDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String trainerEmail = authentication.getName().replace("trainer:", "");
    WorkoutExerciseDTO updatedWorkoutExercise = workoutExerciseService.updateWorkoutExercise(workoutExerciseId,
        workoutExerciseDTO, trainerEmail);
    return ResponseEntity.ok(updatedWorkoutExercise);
  }

  @DeleteMapping("/{workoutExerciseId}")
  public ResponseEntity<String> removeExerciseFromWorkoutPlan(@PathVariable Long workoutExerciseId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String trainerEmail = authentication.getName().replace("trainer:", "");
    workoutExerciseService.removeExerciseFromWorkoutPlan(workoutExerciseId, trainerEmail);
    return ResponseEntity.ok("Exercise removed from workout plan successfully");
  }

  @GetMapping("/workout-plan/{workoutPlanId}")
  public ResponseEntity<List<WorkoutExerciseDTO>> getExercisesInWorkoutPlan(@PathVariable Long workoutPlanId) {
    List<WorkoutExerciseDTO> workoutExercises = workoutExerciseService.getExercisesInWorkoutPlan(workoutPlanId);
    return ResponseEntity.ok(workoutExercises);
  }

  @DeleteMapping("/workout-plan/{workoutPlanId}/all")
  public ResponseEntity<String> removeAllExercisesFromWorkoutPlan(@PathVariable Long workoutPlanId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String trainerEmail = authentication.getName().replace("trainer:", "");
    workoutExerciseService.removeAllExercisesFromWorkoutPlan(workoutPlanId, trainerEmail);
    return ResponseEntity.ok("All exercises removed from workout plan successfully");
  }
}
