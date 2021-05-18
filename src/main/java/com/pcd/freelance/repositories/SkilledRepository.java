package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.IdSkilled;
import com.pcd.freelance.entities.Skilled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkilledRepository extends JpaRepository<Skilled, IdSkilled> {
    @Query("SELECT skill from Skilled skill WHERE skill.idSkilled.idFreelancer = ?1")
    List<Skilled> findAllByFreelancer(Long idFreelancer);
}
