package org.example.repository;

import org.example.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
  List<WorkoutPlan> findByTrainerId(Long trainerId);

  List<WorkoutPlan> findByTrainerIdAndIsActiveTrue(Long trainerId);

  List<WorkoutPlan> findByGoalType(WorkoutPlan.GoalType goalType);

  List<WorkoutPlan> findByDifficultyLevel(WorkoutPlan.DifficultyLevel difficultyLevel);
}
