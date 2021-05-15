package com.pcd.freelance.controllers;


import com.pcd.freelance.entities.Mission;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/Mission")
@CrossOrigin("http://localhost:4200")
public class MissionController {
    @Autowired
    private MissionRepository missionRepository;

    public MissionController(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }
    @PostMapping("/addMission")
    public Mission createMission(@Valid @RequestBody Mission missionRequest)
    {
        return missionRepository.save(missionRequest);
    }
    @PutMapping("/updateMission")
    public Mission updateMission (@Valid @RequestBody Mission missionRequest)
    {
        return missionRepository.findById(missionRequest.getId()).map(mission->{
            mission.setDescription(missionRequest.getDescription());
            mission.setAveragePayment(mission.getAveragePayment());
            mission.setContrat(missionRequest.getContrat());
            mission.setBeginningDate(missionRequest.getBeginningDate());
            mission.setCompleted(missionRequest.getCompleted());
            mission.setDuration(missionRequest.getDuration());
            mission.setInvited(missionRequest.getInvited());
            mission.setHired(missionRequest.getHired());
            mission.setTitle(missionRequest.getTitle());
            return missionRepository.save(mission);
        }).orElseThrow(() -> new ResourceNotFoundException("Mission with Id= " + missionRequest.getId()+ " not found  "));
    }
    @DeleteMapping("/deleteMission/{idMission}")
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
    public Mission getMissionById(@PathVariable Long idMission)
    {
        return missionRepository.findById(idMission).get();
    }

    @GetMapping("/getMisssionByClientAndDescription/{idClient}/{Description}")
    public Mission getMisssionByClientAndDescription(@PathVariable Long idMission)
    {
        return missionRepository.findById(idMission).get();
    }
    @PutMapping("/updateFileMission/{idMission}")
    public Mission updateFileMission(@PathVariable Long idMission,@RequestParam("imageFile") MultipartFile file) throws IOException
    {
        FileOutputStream outputStream = null;
        String projectPath = System.getProperty("user.dir") + "/MissionFiles/"; //projectPath
        String filePath = projectPath + file.getOriginalFilename();
        try {
            outputStream = new FileOutputStream(new File(filePath));
            outputStream.write(file.getInputStream().read());
            outputStream.close();


        } catch (Exception e) {
            System.out.println("Error while saving file");


        }
        finally {
            return missionRepository.findById(idMission).map(mission->{
                mission.setFilePath(filePath);
                return missionRepository.save(mission);
            }).orElseThrow(() -> new ResourceNotFoundException("Mission with Id= " +idMission+ " not found  "));

        }


    }

}
