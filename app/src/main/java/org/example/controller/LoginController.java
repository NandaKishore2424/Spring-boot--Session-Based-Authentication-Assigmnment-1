package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login() {
    return "redirect:/login.html";
  }

  @GetMapping("/register")
  public String register() {
    return "redirect:/register.html";
  }

  @GetMapping("/")
  public String home() {
    return "redirect:/login.html";
  }
}