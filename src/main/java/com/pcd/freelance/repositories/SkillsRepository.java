package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Language;
import com.pcd.freelance.entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillsRepository extends JpaRepository<Skills,Long> {
  Optional<Skills> findByName(String skillName);
}
