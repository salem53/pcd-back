package com.pcd.freelance.repositories;


import com.pcd.freelance.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews,Long> {
}
