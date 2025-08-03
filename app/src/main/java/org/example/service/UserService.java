package org.example.service;

import org.example.dto.AuthResponseDTO;
import org.example.dto.LoginDTO;
import org.example.dto.UserProfileDTO;
import org.example.dto.UserRegistrationDTO;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private TrainerService trainerService;

  public AuthResponseDTO registerUser(UserRegistrationDTO registrationDTO) {
    if (userRepository.existsByEmail(registrationDTO.getEmail())) {
      throw new RuntimeException("Email already exists");
    }

    User user = new User();
    user.setFirstName(registrationDTO.getFirstName());
    user.setLastName(registrationDTO.getLastName());
    user.setEmail(registrationDTO.getEmail());
    user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
    user.setAge(registrationDTO.getAge());

    user.setGender(User.Gender.valueOf(registrationDTO.getGender()));
    user.setFitnessGoal(User.FitnessGoal.valueOf(registrationDTO.getFitnessGoal()));
    user.setCreatedAt(LocalDateTime.now());
    user.setUpdatedAt(LocalDateTime.now());

    User savedUser = userRepository.save(user);
    String token = jwtUtil.generateToken(savedUser.getEmail());

    return new AuthResponseDTO(token, "User registered successfully",
        savedUser.getFirstName() + " " + savedUser.getLastName());
  }

  public AuthResponseDTO loginUser(LoginDTO loginDTO) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

    User user = userRepository.findByEmail(loginDTO.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found"));

    String token = jwtUtil.generateToken(user.getEmail());
    return new AuthResponseDTO(token, "Login successful", user.getFirstName() + " " + user.getLastName());
  }

  public UserProfileDTO getUserProfile(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

    UserProfileDTO profileDTO = new UserProfileDTO();
    profileDTO.setId(user.getId());
    profileDTO.setFirstName(user.getFirstName());
    profileDTO.setLastName(user.getLastName());
    profileDTO.setEmail(user.getEmail());
    profileDTO.setPhone(user.getPhone());
    profileDTO.setAge(user.getAge());
    profileDTO.setHeight(user.getHeight());
    profileDTO.setWeight(user.getWeight());
    profileDTO.setGender(user.getGender().toString());
    profileDTO.setFitnessGoal(user.getFitnessGoal().toString());

    return profileDTO;
  }
}
