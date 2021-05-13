package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Experience;
import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.entities.HavingExperience;
import com.pcd.freelance.entities.IdHavingExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HavingExperienceRepository extends JpaRepository<HavingExperience, IdHavingExperience> {

  //  Optional<HavingExperience> findById(IdHavingExperience id);
}
