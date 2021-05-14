package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Education;
import com.pcd.freelance.entities.Speak;
import com.pcd.freelance.entities.SpeakId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education,Long> {
}
