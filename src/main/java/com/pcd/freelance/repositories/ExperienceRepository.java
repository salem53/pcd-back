package com.pcd.freelance.repositories;


import com.pcd.freelance.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExperienceRepository extends JpaRepository<Experience,Long> {

    List<Experience> findByCompany(String company);

    @Query("SELECT ex from Experience ex WHERE ex.company = ?1 AND ex.positionTitle= ?2")
    List<Experience> findByCompanyAndPositionTitle(String company,String positionTitle);
}
