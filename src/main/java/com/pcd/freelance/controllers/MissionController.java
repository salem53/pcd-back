package com.pcd.freelance.controllers;

import com.pcd.freelance.encryptDecrypt.AES;
import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.entities.IdHavingExperience;
import com.pcd.freelance.entities.Mission;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/Mission")
public class MissionController {
    @Autowired
    private MissionRepository missionRepository;

    public MissionController(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }
    @PostMapping("/NewMission")
    public Mission createMission(@Valid @RequestBody Mission missionRequest)
    {
        return missionRepository.save(missionRequest);
    }
    @PutMapping("/ModifyMission")
    public Mission updateMission (@Valid @RequestBody Mission missionRequest)
    {
        return missionRepository.findById(missionRequest.getId()).map(mission->{
            mission.setDescription(missionRequest.getDescription());
            mission.setAveragePayment(mission.getAveragePayment());
            mission.setContrat(missionRequest.getContrat());
            mission.setBeginningDate(missionRequest.getBeginningDate());
            mission.setCompleted(missionRequest.isCompleted());
            mission.setDuration(missionRequest.getDuration());
            mission.setInvited(missionRequest.getInvited());
            mission.setHired(missionRequest.isHired());
            mission.setTitle(missionRequest.getTitle());
            return missionRepository.save(mission);
        }).orElseThrow(() -> new ResourceNotFoundException("Mission with Id= " + missionRequest.getId()+ " not found  "));
    }
    @DeleteMapping("/DeleteMission/{idMission}")
    public ResponseEntity<?> deleteMission (@PathVariable Long idMission)
    {
        return missionRepository.findById(idMission).map(
                mission -> {
                    missionRepository.delete(mission);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Mission with Id= " +
                idMission + " not found"));
    }
    @GetMapping("/getMisssion/{idMission}")
    public Mission getMissionById(@PathVariable Long idMission) {
        return missionRepository.findById(idMission).get();
    }

}