package org.example.service;

import org.example.dto.ExerciseDTO;
import org.example.entity.Exercise;
import org.example.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

  @Autowired
  private ExerciseRepository exerciseRepository;

  public ExerciseDTO createExercise(ExerciseDTO exerciseDTO) {
    Exercise exercise = new Exercise();
    exercise.setExerciseName(exerciseDTO.getExerciseName());
    exercise.setDescription(exerciseDTO.getDescription());
    exercise.setCategory(Exercise.ExerciseCategory.valueOf(exerciseDTO.getCategory()));
    exercise.setEquipmentType(Exercise.EquipmentType.valueOf(exerciseDTO.getEquipmentType()));
    exercise.setMuscleGroups(exerciseDTO.getMuscleGroups());
    exercise.setInstructions(exerciseDTO.getInstructions());
    exercise.setCreatedAt(LocalDateTime.now());
    exercise.setUpdatedAt(LocalDateTime.now());

    Exercise savedExercise = exerciseRepository.save(exercise);
    return convertToDTO(savedExercise);
  }

  public ExerciseDTO updateExercise(Long exerciseId, ExerciseDTO exerciseDTO) {
    Exercise exercise = exerciseRepository.findById(exerciseId)
        .orElseThrow(() -> new RuntimeException("Exercise not found"));

    exercise.setExerciseName(exerciseDTO.getExerciseName());
    exercise.setDescription(exerciseDTO.getDescription());
    exercise.setCategory(Exercise.ExerciseCategory.valueOf(exerciseDTO.getCategory()));
    exercise.setEquipmentType(Exercise.EquipmentType.valueOf(exerciseDTO.getEquipmentType()));
    exercise.setMuscleGroups(exerciseDTO.getMuscleGroups());
    exercise.setInstructions(exerciseDTO.getInstructions());
    exercise.setUpdatedAt(LocalDateTime.now());

    Exercise savedExercise = exerciseRepository.save(exercise);
    return convertToDTO(savedExercise);
  }

  public void deleteExercise(Long exerciseId) {
    Exercise exercise = exerciseRepository.findById(exerciseId)
        .orElseThrow(() -> new RuntimeException("Exercise not found"));
    exerciseRepository.delete(exercise);
  }

  public ExerciseDTO getExerciseById(Long exerciseId) {
    Exercise exercise = exerciseRepository.findById(exerciseId)
        .orElseThrow(() -> new RuntimeException("Exercise not found"));
    return convertToDTO(exercise);
  }

  public List<ExerciseDTO> getAllExercises() {
    List<Exercise> exercises = exerciseRepository.findAll();
    return exercises.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  public List<ExerciseDTO> getExercisesByCategory(String category) {
    Exercise.ExerciseCategory exerciseCategory = Exercise.ExerciseCategory.valueOf(category);
    List<Exercise> exercises = exerciseRepository.findByCategory(exerciseCategory);
    return exercises.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  public List<ExerciseDTO> searchExercises(String name) {
    List<Exercise> exercises = exerciseRepository.findByExerciseNameContainingIgnoreCase(name);
    return exercises.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  private ExerciseDTO convertToDTO(Exercise exercise) {
    ExerciseDTO dto = new ExerciseDTO();
    dto.setId(exercise.getId());
    dto.setExerciseName(exercise.getExerciseName());
    dto.setDescription(exercise.getDescription());
    dto.setCategory(exercise.getCategory().toString());
    dto.setEquipmentType(exercise.getEquipmentType().toString());
    dto.setMuscleGroups(exercise.getMuscleGroups());
    dto.setInstructions(exercise.getInstructions());
    return dto;
  }
}
