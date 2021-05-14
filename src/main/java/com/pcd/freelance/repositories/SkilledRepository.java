package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Skilled;
import com.pcd.freelance.entities.SkilledId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkilledRepository extends JpaRepository<Skilled, SkilledId> {
}
