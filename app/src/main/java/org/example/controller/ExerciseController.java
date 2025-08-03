package org.example.controller;

import org.example.dto.ExerciseDTO;
import org.example.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@CrossOrigin(origins = "*")
public class ExerciseController {

  @Autowired
  private ExerciseService exerciseService;

  @PostMapping
  public ResponseEntity<ExerciseDTO> createExercise(@Valid @RequestBody ExerciseDTO exerciseDTO) {
    ExerciseDTO createdExercise = exerciseService.createExercise(exerciseDTO);
    return ResponseEntity.ok(createdExercise);
  }

  @PutMapping("/{exerciseId}")
  public ResponseEntity<ExerciseDTO> updateExercise(@PathVariable Long exerciseId,
      @Valid @RequestBody ExerciseDTO exerciseDTO) {
    ExerciseDTO updatedExercise = exerciseService.updateExercise(exerciseId, exerciseDTO);
    return ResponseEntity.ok(updatedExercise);
  }

  @DeleteMapping("/{exerciseId}")
  public ResponseEntity<String> deleteExercise(@PathVariable Long exerciseId) {
    exerciseService.deleteExercise(exerciseId);
    return ResponseEntity.ok("Exercise deleted successfully");
  }

  @GetMapping("/{exerciseId}")
  public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable Long exerciseId) {
    ExerciseDTO exercise = exerciseService.getExerciseById(exerciseId);
    return ResponseEntity.ok(exercise);
  }

  @GetMapping
  public ResponseEntity<List<ExerciseDTO>> getAllExercises() {
    List<ExerciseDTO> exercises = exerciseService.getAllExercises();
    return ResponseEntity.ok(exercises);
  }

  @GetMapping("/category/{category}")
  public ResponseEntity<List<ExerciseDTO>> getExercisesByCategory(@PathVariable String category) {
    List<ExerciseDTO> exercises = exerciseService.getExercisesByCategory(category);
    return ResponseEntity.ok(exercises);
  }

  @GetMapping("/search")
  public ResponseEntity<List<ExerciseDTO>> searchExercises(@RequestParam String name) {
    List<ExerciseDTO> exercises = exerciseService.searchExercises(name);
    return ResponseEntity.ok(exercises);
  }
}
