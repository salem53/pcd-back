package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Study;
import com.pcd.freelance.entities.IdStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study, IdStudy> {


    @Query("SELECT s from Study s  WHERE s.idStudy.idFreelancer= ?1 ")
    List<Study> findAllByIdFreelancer(Long idFreelancer);
}
