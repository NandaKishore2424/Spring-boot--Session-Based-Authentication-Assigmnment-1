package org.example.controller;
import org.example.dto.UserRegistrationDTO;
import org.example.dto.LoginDTO;
import org.example.dto.AuthResponseDTO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/register", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<AuthResponseDTO> registerUser(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        AuthResponseDTO response = userService.registerUser(registrationDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/register", consumes = "application/x-www-form-urlencoded")
    public String registerUserForm(UserRegistrationDTO registrationDTO) {
        userService.registerUser(registrationDTO);
        return "redirect:/login.html";
    }
    @PostMapping(value = "/login", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<AuthResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO response = userService.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }
}
