package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer,Long> {

    Optional<Freelancer> findByEmail(String email);
}
