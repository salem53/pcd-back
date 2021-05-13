package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Certification;
import com.pcd.freelance.entities.Certified;
import com.pcd.freelance.entities.IdCertified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertifiedRepository extends JpaRepository<Certified, IdCertified> {

    @Query("UPDATE Certified f SET f.file=?1  WHERE f.freelancer= ?2 AND f.certification= ?3")
    void updateFileById(String file, Long idFreelancer, Long idCertification);
}
