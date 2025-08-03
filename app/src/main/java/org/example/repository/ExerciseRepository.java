package org.example.repository;

import org.example.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
  List<Exercise> findByCategory(Exercise.ExerciseCategory category);

  List<Exercise> findByEquipmentType(Exercise.EquipmentType equipmentType);

  List<Exercise> findByExerciseNameContainingIgnoreCase(String exerciseName);
}
