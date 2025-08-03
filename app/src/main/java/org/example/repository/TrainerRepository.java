package org.example.repository;

import org.example.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
  Optional<Trainer> findByEmail(String email);

  Boolean existsByEmail(String email);

  Boolean existsByCertificationNumber(String certificationNumber);
}
