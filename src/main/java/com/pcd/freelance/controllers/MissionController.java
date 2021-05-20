package com.pcd.freelance.controllers;


import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.entities.Mission;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
        System.out.println(missionRequest);
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
        System.out.println("hero");
        return missionRepository.findMissionById(idMission);
    }

    //get hired missions
    @GetMapping("/getHiredMissions")
    public List<Mission> getHiredMissions(){
        return missionRepository.findByHiredOrCompleted("true","false");
    }

    //get hired missions with client id
    @GetMapping("/getHiredMissions/clients/{clientId}")
    public List<Mission> getHiredMissionsWithClientId(@PathVariable Long clientId){
        return missionRepository.findByHiredOrCompletedWithClientId("true","false",clientId);
    }
    //get completed missions with client id
    @GetMapping("/getCompletedMissions/clients/{clientId}")
    public List<Mission> getCompletedMissionsWithClientId(@PathVariable Long clientId){
        return missionRepository.findByHiredOrCompletedWithClientId("true","true",clientId);
    }
    //set mission as completed
    @GetMapping("/setMissionAsCompleted/mission/{idMission}")
    public Mission setMissionToCopmleted(@PathVariable Long idMission)

    {
        Mission mission = missionRepository.findById(idMission).get();
        mission.setCompleted("true");
        return missionRepository.save(mission);
    }

    //get hired missions with freelancer id
    @GetMapping("/getHiredMissions/freelancers/{freelancerId}")
    public List<Mission> getHiredMissionsWithFreelancerId(@PathVariable Long freelancerId){
        return missionRepository.findByHiredOrCompletedWithFreelancerId("true","false",freelancerId);
    }

    //get completed missions with freelancer id
    @GetMapping("/getCompletedMissions/freelancers/{freelancerId}")
    public List<Mission> getCompletedMissionsWithFreelancerId(@PathVariable Long freelancerId){
        return missionRepository.findByHiredOrCompletedWithFreelancerId("true","true",freelancerId);
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


    @PutMapping("/updateListInvitedMission/{idMission}/{idFreelancer}")
    public Mission updateListInvitedMission(@PathVariable Long idMission,@PathVariable Long idFreelancer) throws IOException
    {


            return missionRepository.findById(idMission).map(mission->{
                mission.setListInvited(mission.getListInvited()+idFreelancer);

                return missionRepository.save(mission);
            }).orElseThrow(() -> new ResourceNotFoundException("Mission with Id= " +idMission+ " not found  "));




    }
    @PutMapping("/updateListAppliedMission/{idMission}/{idFreelancer}")
    public Mission updateListAppliedMission(@PathVariable Long idMission,@PathVariable Long idFreelancer,@Valid @RequestBody Freelancer f)
    {

      /*  System.out.println("null");
        return missionRepository.findById(idMission).map(mission->{
            if(mission.getListApplied()==null) {
                System.out.println("null");
                mission.setListApplied(Long.toString(idFreelancer));
            }
            else
            {
                System.out.println("non vide");
            }

            return missionRepository.save(mission);
        }).orElseThrow(() -> new ResourceNotFoundException("Mission with Id= " +idMission+ " not found  "));
*/
        String n;
        if(missionRepository.findById(idMission).get().getListApplied()==null)
        {   n=Long.toString(idFreelancer);
        System.out.println(n);}
        else
            n=missionRepository.findById(idMission).get().getListApplied()+Long.toString(idFreelancer);
        return missionRepository.updateAppliedMission(n,idMission);



    }

}
