package org.example.controller;

import org.example.dto.AuthResponseDTO;
import org.example.dto.LoginDTO;
import org.example.dto.TrainerRegistrationDTO;
import org.example.entity.Trainer; 
import org.example.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; 
import org.example.util.JwtUtil;

@RestController
@RequestMapping("/api/trainer/auth")
@CrossOrigin(origins = "*")
public class TrainerAuthController {

  @Autowired
  private TrainerService trainerService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/register")
  public ResponseEntity<AuthResponseDTO> registerTrainer(@Valid @RequestBody TrainerRegistrationDTO registrationDTO) {
    AuthResponseDTO response = trainerService.registerTrainer(registrationDTO);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> loginTrainer(@Valid @RequestBody LoginDTO loginDTO) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken("trainer:" + loginDTO.getEmail(), loginDTO.getPassword()));

    Trainer trainer = trainerService.findByEmail(loginDTO.getEmail());
    String token = jwtUtil.generateToken("trainer:" + trainer.getEmail());

    return ResponseEntity.ok(new AuthResponseDTO(token, "Login successful",
        trainer.getFirstName() + " " + trainer.getLastName()));
  }
}
