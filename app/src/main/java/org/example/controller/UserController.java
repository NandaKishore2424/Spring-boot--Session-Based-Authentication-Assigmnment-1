package org.example.controller;

import org.example.dto.UserProfileDTO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
  @Autowired
  private UserService userService;
  @GetMapping("/profile")
  public ResponseEntity<UserProfileDTO> getUserProfile() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    UserProfileDTO profile = userService.getUserProfile(email);
    return ResponseEntity.ok(profile);
  }
  @GetMapping("/dashboard")
  public ResponseEntity<String> getDashboard() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    return ResponseEntity.ok("Dashboard data for user: " + email);
  }
}
