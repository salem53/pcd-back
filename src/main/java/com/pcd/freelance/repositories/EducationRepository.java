package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education,Long> {

    @Query("SELECT e from Education e  WHERE e.school= ?1 AND e.degree= ?2")
    List<Education> findBySchoolAndDegree(String school, String degree);
}
