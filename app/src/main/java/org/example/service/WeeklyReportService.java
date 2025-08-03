package org.example.service;

import org.example.dto.WeeklyReportDTO;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WeeklyReportService {

  @Autowired
  private UserRepository userRepository;

  public WeeklyReportDTO getWeeklyReport(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
    return generateSimulatedWeeklyReport(user);
  }

  private WeeklyReportDTO generateSimulatedWeeklyReport(User user) {
    Random random = new Random();
    WeeklyReportDTO report = new WeeklyReportDTO();
    LocalDate today = LocalDate.now();
    LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
    LocalDate endOfWeek = startOfWeek.plusDays(6);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");
    report.setWeekRange(startOfWeek.format(formatter) + " - " + endOfWeek.format(formatter));

    int currentWeekCalories = 1500 + random.nextInt(1000);
    int currentWeekExercises = 10 + random.nextInt(15);

    int previousWeekCalories = currentWeekCalories - (200 + random.nextInt(300));
    int previousWeekExercises = currentWeekExercises - (2 + random.nextInt(5));
    
    previousWeekCalories = Math.max(previousWeekCalories, 0);
    previousWeekExercises = Math.max(previousWeekExercises, 0);

    double caloriesProgress = 0;
    double exercisesProgress = 0;
    
    if (previousWeekCalories > 0) {
      caloriesProgress = ((double) currentWeekCalories / previousWeekCalories - 1) * 100;
    }
    
    if (previousWeekExercises > 0) {
      exercisesProgress = ((double) currentWeekExercises / previousWeekExercises - 1) * 100;
    }

    report.setTotalCaloriesBurned(currentWeekCalories);
    report.setTotalExercisesCompleted(currentWeekExercises);
    report.setPreviousWeekCaloriesBurned(previousWeekCalories);
    report.setPreviousWeekExercisesCompleted(previousWeekExercises);
    report.setCaloriesProgressPercent(Math.round(caloriesProgress * 10) / 10.0);
    report.setExercisesProgressPercent(Math.round(exercisesProgress * 10) / 10.0);

    List<WeeklyReportDTO.ExerciseSummary> exerciseSummaries = new ArrayList<>();
    
    String[] exercises = {"Running", "Push-ups", "Squats", "Lunges", "Planks", "Cycling"};
    for (String exerciseName : exercises) {
      if (random.nextBoolean()) { 
        WeeklyReportDTO.ExerciseSummary summary = new WeeklyReportDTO.ExerciseSummary();
        summary.setExerciseName(exerciseName);
        summary.setTimesCompleted(1 + random.nextInt(5));
        summary.setCaloriesBurned(100 + random.nextInt(300));
        exerciseSummaries.add(summary);
      }
    }
    
    report.setExerciseSummaries(exerciseSummaries);

    return report;
  }
}