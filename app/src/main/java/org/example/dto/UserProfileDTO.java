package org.example.dto;
public class UserProfileDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private Integer age;
  private Double height;
  private Double weight;
  private String gender;
  private String fitnessGoal;
  public UserProfileDTO() {
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getFitnessGoal() {
    return fitnessGoal;
  }

  public void setFitnessGoal(String fitnessGoal) {
    this.fitnessGoal = fitnessGoal;
  }
}
