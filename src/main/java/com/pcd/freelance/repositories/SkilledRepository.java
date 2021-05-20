package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.entities.IdSkilled;
import com.pcd.freelance.entities.Skilled;
import com.pcd.freelance.entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkilledRepository extends JpaRepository<Skilled, IdSkilled> {
    @Query("SELECT skill from Skilled skill WHERE skill.idSkilled.idFreelancer = ?1")
    List<Skilled> findAllByFreelancer(Long idFreelancer);

    /*@Query(value = "SELECT freelancer FROM Skilled skill WHERE skill.skill.name= ?1",
            nativeQuery = true)
    List<Freelancer> findFreelancersBySkill(String skillName);*/

}
