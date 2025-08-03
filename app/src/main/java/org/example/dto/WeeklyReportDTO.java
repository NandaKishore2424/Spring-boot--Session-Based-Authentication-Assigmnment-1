package org.example.dto;

import java.util.List;

public class WeeklyReportDTO {
  private String weekRange;
  private int totalCaloriesBurned;
  private int totalExercisesCompleted;
  private int previousWeekCaloriesBurned;
  private int previousWeekExercisesCompleted;
  private double caloriesProgressPercent;
  private double exercisesProgressPercent;
  private List<ExerciseSummary> exerciseSummaries;

  public static class ExerciseSummary {
    private String exerciseName;
    private int timesCompleted;
    private int caloriesBurned;

    public String getExerciseName() {
      return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
      this.exerciseName = exerciseName;
    }

    public int getTimesCompleted() {
      return timesCompleted;
    }

    public void setTimesCompleted(int timesCompleted) {
      this.timesCompleted = timesCompleted;
    }

    public int getCaloriesBurned() {
      return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
      this.caloriesBurned = caloriesBurned;
    }
  }

  public String getWeekRange() {
    return weekRange;
  }

  public void setWeekRange(String weekRange) {
    this.weekRange = weekRange;
  }

  public int getTotalCaloriesBurned() {
    return totalCaloriesBurned;
  }

  public void setTotalCaloriesBurned(int totalCaloriesBurned) {
    this.totalCaloriesBurned = totalCaloriesBurned;
  }

  public int getTotalExercisesCompleted() {
    return totalExercisesCompleted;
  }

  public void setTotalExercisesCompleted(int totalExercisesCompleted) {
    this.totalExercisesCompleted = totalExercisesCompleted;
  }

  public int getPreviousWeekCaloriesBurned() {
    return previousWeekCaloriesBurned;
  }

  public void setPreviousWeekCaloriesBurned(int previousWeekCaloriesBurned) {
    this.previousWeekCaloriesBurned = previousWeekCaloriesBurned;
  }

  public int getPreviousWeekExercisesCompleted() {
    return previousWeekExercisesCompleted;
  }

  public void setPreviousWeekExercisesCompleted(int previousWeekExercisesCompleted) {
    this.previousWeekExercisesCompleted = previousWeekExercisesCompleted;
  }

  public double getCaloriesProgressPercent() {
    return caloriesProgressPercent;
  }

  public void setCaloriesProgressPercent(double caloriesProgressPercent) {
    this.caloriesProgressPercent = caloriesProgressPercent;
  }

  public double getExercisesProgressPercent() {
    return exercisesProgressPercent;
  }

  public void setExercisesProgressPercent(double exercisesProgressPercent) {
    this.exercisesProgressPercent = exercisesProgressPercent;
  }

  public List<ExerciseSummary> getExerciseSummaries() {
    return exerciseSummaries;
  }

  public void setExerciseSummaries(List<ExerciseSummary> exerciseSummaries) {
    this.exerciseSummaries = exerciseSummaries;
  }
}
