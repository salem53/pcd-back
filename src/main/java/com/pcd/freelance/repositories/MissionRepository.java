package com.pcd.freelance.repositories;



import com.pcd.freelance.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission,Long> {
    @Query("SELECT mission from Mission  mission  WHERE mission.hired= ?1 AND mission.completed= ?2")
    List<Mission> findByHiredOrCompleted(String hired, String completed);

    @Query("SELECT mission from Mission  mission  WHERE mission.hired= ?1 AND mission.completed= ?2 AND mission.client.id= ?3")
    List<Mission> findByHiredOrCompletedWithClientId(String hired, String completed,Long idClient);

    @Query("SELECT SUM(m.averagePayment) FROM Mission m")
    Integer getSum();
    @Query("SELECT mission from Mission  mission  WHERE mission.hired= ?1 AND mission.completed= ?2 AND mission.freelancer.id= ?3")
    List<Mission> findByHiredOrCompletedWithFreelancerId(String hired, String completed,Long idFreelancer);


    @Query("SELECT mission from Mission  mission  WHERE mission.id= ?1")
    Mission findMissionById(Long idMission);

    @Query("UPDATE Mission m SET m.listApplied= ?1  WHERE m.id= ?2 ")
    Mission updateAppliedMission(String idFreelancer,Long idMission);

    @Query("SELECT mission from Mission  mission  WHERE mission.completed= ?2 AND mission.freelancer.id= ?1 ")
    List<Mission> findMissionsHiredToFreelancer(Long idfreelancer,String compelted);

    @Query("SELECT mission.id,mission.listInvited  from Mission  mission ")
    List<List> getListInvited();

    @Query("SELECT mission.listAcceptedMissions  from Mission  mission where mission.id=?1 ")
    String getListAcceptedInvitation(Long idMission);

    //list of the missions
    @Query("SELECT mission.id,mission.listApplied  from Mission  mission where mission.freelancer.id <> ?1 or mission.freelancer.id is null ")
    List<List> getAppliedMissions(Long idfreelancer);
}
