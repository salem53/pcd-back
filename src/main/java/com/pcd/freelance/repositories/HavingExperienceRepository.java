package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.HavingExperience;
import com.pcd.freelance.entities.IdHavingExperience;
import org.springframework.data.jpa.repository.JpaRepository;



public interface HavingExperienceRepository extends JpaRepository<HavingExperience, IdHavingExperience> {


}
