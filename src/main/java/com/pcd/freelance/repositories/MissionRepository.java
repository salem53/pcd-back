package com.pcd.freelance.repositories;



import com.pcd.freelance.entities.Certification;
import com.pcd.freelance.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission,Long> {
    @Query("SELECT mission from Mission  mission  WHERE mission.hired= ?1 AND mission.completed= ?2")
    List<Mission> findByHiredOrCompleted(String hired, String completed);

    @Query("SELECT mission from Mission  mission  WHERE mission.hired= ?1 AND mission.completed= ?2 AND mission.client.id= ?3")
    List<Mission> findByHiredOrCompletedWithClientId(String hired, String completed,Long idClient);

    @Query("SELECT mission from Mission  mission  WHERE mission.hired= ?1 AND mission.completed= ?2 AND mission.freelancer.id= ?3")
    List<Mission> findByHiredOrCompletedWithFreelancerId(String hired, String completed,Long idFreelancer);


}
