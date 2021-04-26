package com.pcd.freelance.repositories;



import com.pcd.freelance.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission,Long> {
}
