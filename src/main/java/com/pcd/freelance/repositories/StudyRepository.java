package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Study;
import com.pcd.freelance.entities.IdStudy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, IdStudy> {
}
