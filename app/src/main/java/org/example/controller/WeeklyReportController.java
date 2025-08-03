package org.example.controller;

import org.example.dto.WeeklyReportDTO;
import org.example.service.WeeklyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/report")
public class WeeklyReportController {

  @Autowired
  private WeeklyReportService weeklyReportService;

  @GetMapping("/weekly")
  public ResponseEntity<WeeklyReportDTO> getWeeklyReport(Authentication authentication) {
    String email = authentication.getName();
    WeeklyReportDTO report = weeklyReportService.getWeeklyReport(email);
    return ResponseEntity.ok(report);
  }
}
