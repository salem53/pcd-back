package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Language;
import com.pcd.freelance.entities.Skilled;
import com.pcd.freelance.entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface SkillsRepository extends JpaRepository<Skills,Long> {

 // @Query("SELECT certif from Certification certif  WHERE certif.name= ?1 AND certif.organism= ?2")
  List<Skills> findByName(String skillName);


}
