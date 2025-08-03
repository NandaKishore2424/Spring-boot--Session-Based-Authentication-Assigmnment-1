package org.example.service;

import org.example.dto.AuthResponseDTO;
import org.example.dto.TrainerRegistrationDTO;
import org.example.entity.Trainer;
import org.example.repository.TrainerRepository;
import org.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TrainerService {

  @Autowired
  private TrainerRepository trainerRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  public AuthResponseDTO registerTrainer(TrainerRegistrationDTO registrationDTO) {
    if (trainerRepository.existsByEmail(registrationDTO.getEmail())) {
      throw new RuntimeException("Email already exists");
    }

    if (registrationDTO.getCertificationNumber() != null &&
        trainerRepository.existsByCertificationNumber(registrationDTO.getCertificationNumber())) {
      throw new RuntimeException("Certification number already exists");
    }

    Trainer trainer = new Trainer();
    trainer.setFirstName(registrationDTO.getFirstName());
    trainer.setLastName(registrationDTO.getLastName());
    trainer.setEmail(registrationDTO.getEmail());
    trainer.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
    trainer.setPhone(registrationDTO.getPhone());
    trainer.setCertificationNumber(registrationDTO.getCertificationNumber());
    trainer.setSpecialization(registrationDTO.getSpecialization());
    trainer.setExperienceYears(registrationDTO.getExperienceYears());
    trainer.setIsVerified(false);
    trainer.setCreatedAt(LocalDateTime.now());
    trainer.setUpdatedAt(LocalDateTime.now());

    Trainer savedTrainer = trainerRepository.save(trainer);
    String token = jwtUtil.generateToken("trainer:" + savedTrainer.getEmail());

    return new AuthResponseDTO(token, "Trainer registered successfully",
        savedTrainer.getFirstName() + " " + savedTrainer.getLastName());
  }

  public Trainer findByEmail(String email) {
    return trainerRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Trainer not found"));
  }

  public UserDetails loadTrainerByUsername(String email) {
    Trainer trainer = findByEmail(email);
    return new org.springframework.security.core.userdetails.User(
        trainer.getEmail(),
        trainer.getPassword(),
        new ArrayList<>());
  }
}
