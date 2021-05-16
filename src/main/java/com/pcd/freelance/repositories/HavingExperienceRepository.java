package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.HavingExperience;
import com.pcd.freelance.entities.IdHavingExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface HavingExperienceRepository extends JpaRepository<HavingExperience, IdHavingExperience> {

    @Query("SELECT exp from HavingExperience exp WHERE exp.idHavingExperience.idFreelancer= ?1 ")
    List<HavingExperience> findAllExperiencesByIdFreelancer(Long idFreelancer);
}
