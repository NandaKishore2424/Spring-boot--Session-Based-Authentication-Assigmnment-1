package org.example.repository;

import org.example.entity.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
  List<WorkoutExercise> findByWorkoutPlanIdOrderByOrderIndex(Long workoutPlanId);

  List<WorkoutExercise> findByExerciseId(Long exerciseId);

  void deleteByWorkoutPlanId(Long workoutPlanId);
}
