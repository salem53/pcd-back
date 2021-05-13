package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification,Long> {

    @Query("SELECT certif from Certification certif  WHERE certif.name= ?1 AND certif.organism= ?2")
    List<Certification> findByNameAndOrganism(String name, String organism);
}
