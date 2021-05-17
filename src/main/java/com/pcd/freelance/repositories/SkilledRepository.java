package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.IdSkilled;
import com.pcd.freelance.entities.Skilled;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkilledRepository extends JpaRepository<Skilled, IdSkilled> {
}
