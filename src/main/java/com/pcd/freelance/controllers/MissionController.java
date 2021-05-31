package com.pcd.freelance.controllers;


import com.pcd.freelance.entities.*;

import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.ClientRepository;
import com.pcd.freelance.repositories.FreelancerRepository;
import com.pcd.freelance.repositories.MissionRepository;
import com.pcd.freelance.repositories.SkilledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.validation.Valid;
import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/Mission")
@CrossOrigin("http://localhost:4200")
public class MissionController  {
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private SkilledRepository skilledRepository;

    @Autowired
    private FreelancerRepository freelancerR;

    @Autowired
    private ClientRepository clientR;

//    public MissionController(FreelancerRepository freelancerR, ClientRepository clientR, MissionRepository missionRepository, SkilledRepository skilledRepository) {
//
//        this.missionRepository = missionRepository;
//        this.skilledRepository=skilledRepository;
//        this.clientR = clientR;
//        this.freelancerR = freelancerR;
//    }
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

    //get Statistics
    @GetMapping("/getStatistics")
    public Statistics getStatistics() {
        Statistics statistics = new Statistics();
        statistics.setFreelancersNumber(freelancerR.count());
        statistics.setClientsNumber(clientR.count());
        statistics.setTotalMissionsNumber(missionRepository.count());
        statistics.setHiredMissionsNumber(missionRepository.findByHiredOrCompleted("true","false").size());
        statistics.setCompletedMissionsNumber(missionRepository.findByHiredOrCompleted("true","true").size());
        statistics.setTotalFreelancersNationalities(freelancerR.findNationalities().size() );
        statistics.setTotalClientssNationalities(clientR.findNationalities().size() );
        statistics.setTotalTransactionNumber(missionRepository.getSum());

        return statistics;
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
    //get not hired missions with skills
    @GetMapping("/getNotHiredMissionsBySkills/missions/notHired/{skills}")
    public List<Mission> getNotHiredMissionsWithSkills(@PathVariable String skills){
        String[] skillsList = skills.split(" ");
        System.out.println("cv");
        List<Mission> allMissions = missionRepository.findByHiredOrCompleted("false","false");

        List<Mission> selectedMissions = new ArrayList<>();
        for (int i=0;i<allMissions.size();i++){
            for(String skill : skillsList) {

                if(allMissions.get(i).getTechnologies()!=null && allMissions.get(i).getTechnologies().contains(skill)){
                    selectedMissions.add(allMissions.get(i));
                }
            }

        }
        return selectedMissions;


    }
    //get freelancers by skills
    @GetMapping("/getMissionsBySkills/missions/freelancers/geta/{skills}")
    public  List<FreelancerWithSkills> getFreelancersWithSkills(@PathVariable String skills) {
        /*String[] skillsList = skills.split(" ");*/
       List<Skilled> allSKilledFreelancers= skilledRepository.findAll();
       List<FreelancerWithSkills> selectedFreelancers = new ArrayList<>();
       for (Skilled skilled : allSKilledFreelancers){
           if(skills!= null && skills.contains(skilled.getSkill().getName())){
               Freelancer freelancer =skilled.getFreelancer();
                FreelancerWithSkills freelancerWithSkills = new FreelancerWithSkills();
               if(freelancer != null) {
                   List<Skilled> freelancerSkills = skilledRepository.findAllByFreelancer(freelancer.getId());
                   ArrayList<String> skillsOf = new ArrayList<>();
                   for(Skilled skilllled : freelancerSkills) {
                       skillsOf.add(skilllled.getSkill().getName());
                   }
                   if(freelancer.getImage()!=null){
                       freelancer.setImage(decompressBytes(freelancer.getImage()));
                   }
                   freelancerWithSkills.setSkills(skillsOf);
               }
                freelancerWithSkills.setFreelancer(freelancer);
               selectedFreelancers.add(freelancerWithSkills);
           }

       }
        return selectedFreelancers;
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
    //get Completed Missions
    @GetMapping("/getCompletedMissions")
    public List<Mission> getCompletedMissions()
    {
        return missionRepository.findByHiredOrCompleted("true","true");
    }
    //set mission as hired
    @GetMapping("/setMissionAsHired/{missionId}/{freelancerId}")
    public Mission setMissionAsHired(@PathVariable Long missionId,@PathVariable Long freelancerId){
        Mission mission = missionRepository.findById(missionId).get();
        Freelancer freelancer = freelancerR.findById(freelancerId).get();
        mission.setHired("true");
        mission.setFreelancer(freelancer);

       return missionRepository.save(mission);
    }
    //get Not Hired Mission With Client


    @GetMapping("/getNotHiredMissions/clients/{clientId}")
    public List<MissionNotHiredWithApplicationsAndInvitedAndAcceptedInvitations> getNotHiredMissionsWithClientId(@PathVariable Long clientId){
        List<Mission> missions = missionRepository.findByHiredOrCompletedWithClientId("false","false",clientId);

        List<MissionNotHiredWithApplicationsAndInvitedAndAcceptedInvitations> selectedMissions= new ArrayList<>();
        for(Mission mission : missions) {

            MissionNotHiredWithApplicationsAndInvitedAndAcceptedInvitations missionToAdd = new MissionNotHiredWithApplicationsAndInvitedAndAcceptedInvitations();
            missionToAdd.setMission(mission);
            List<Freelancer> invitedFreelancers = new ArrayList<>();
            List<Freelancer> freelancersAcceptedInvitations = new ArrayList<>();
            List<Freelancer> appliedFreelancers = new ArrayList<>();
            List<String> invitedFreelancersIds = new ArrayList<>();
            List<String> freelancersAcceptedInvitationsIds = new ArrayList<>();
            List<String> appliedFreelancersIds = new ArrayList<>();
            /*List<String> invitedFreelancersIds = Arrays.asList(mission.getListInvited().split("/"));*/
            System.out.println(invitedFreelancersIds);
            /*List<String> freelancersAcceptedInvitationsIds = Arrays.asList(mission.getListAcceptedInvitation().split("/"));
            List<String> appliedFreelancersIds = Arrays.asList(mission.getListApplied().split("/"));*/
            if (mission.getListInvited() != "" ) {
                invitedFreelancersIds = Arrays.asList(mission.getListInvited().split("/"));
                for (String id : invitedFreelancersIds) {
                    if (id != "" && id.length()>0) {
                        invitedFreelancers.add(freelancerR.findById(Long.valueOf(id)).get());
                    }
                }

                if (mission.getListApplied() != "") {
                    appliedFreelancersIds = Arrays.asList(mission.getListApplied().split("/"));
                    for (String id : appliedFreelancersIds) {
                        if (id != "" && id.length()>0) {
                            appliedFreelancers.add(freelancerR.findById(Long.valueOf(id)).get());
                        }
                    }
                }

                if (mission.getListAcceptedInvitation() != "") {
                    freelancersAcceptedInvitationsIds = Arrays.asList(mission.getListAcceptedInvitation().split("/"));
                    for (String id : freelancersAcceptedInvitationsIds) {
                        if (id != "" && id.length()>0) {
                            freelancersAcceptedInvitations.add(freelancerR.findById(Long.valueOf(id)).get());
                        }
                    }

                }


                missionToAdd.setInvitedFreelancers(invitedFreelancers);
                missionToAdd.setAppliedFreelancers(appliedFreelancers);
                missionToAdd.setFreelancersAcceptedInvitations(freelancersAcceptedInvitations);
                selectedMissions.add(missionToAdd);

            }
        }
        return selectedMissions;
    }

    /*//get not hired missions with freelancer id
    @GetMapping("/getNotHiredMissions/freelancers/{freelancerId}")
    public List<Mission> getNotHiredMissionsWithFreelancerId(@PathVariable Long freelancerId){
        return missionRepository.findByHiredOrCompletedWithFreelancerId("false","false",freelancerId);
    }*/



    // compress the image bytes before storing it in the database

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();

        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }

        return outputStream.toByteArray();
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
    @GetMapping("/getMisssionsHiredToFreelancer/{idFreelancer}")
    public List<Mission> getMisssionsHiredToFreelancer(@PathVariable Long idFreelancer)
    {
        return missionRepository.findMissionsHiredToFreelancer(idFreelancer,"false");
    }


    @GetMapping("/getInvitationListToFreelancer/{idFreelancer}") //get the list of missions that this freelancer is invited for
    public  List<Optional>  getInvitationListToFreelancer(@PathVariable Long idFreelancer)
    {
        List<Optional> listMissions=new ArrayList<Optional>();
        List<List> liste;
        liste=this.missionRepository.getListInvited();
       String listeAccepted;
        for (List l : liste)
        {

            if((((String)l.get(1)).indexOf(Long.toString(idFreelancer)))>-1) //test if it is in the list of invited ones for this mission
            {
                listeAccepted=this.missionRepository.getListAcceptedInvitation((Long)l.get(0)); //l.get(0) est l'id du mission et l.get(1) est la listInvited d"une mission
                //System.out.println(((String)l.get(1)).indexOf(Long.toString(idFreelancer)));
                if(listeAccepted.indexOf(Long.toString(idFreelancer)) ==-1) {
                    listMissions.add(this.missionRepository.findById((Long) l.get(0)));
                    System.out.println(listeAccepted.indexOf(Long.toString(idFreelancer)));
                }
            }

        }

        return listMissions;
        
    }

    @PutMapping("/updateListAcceptedInvitations/{idMission}/{idFreelancer}")
    public Mission updateListAcceptedInvitations(@PathVariable Long idMission,@PathVariable Long idFreelancer)
    {
        return missionRepository.findById(idMission).map(mission->{
            mission.setListAcceptedInvitation(mission.getListAcceptedInvitation()+'/'+idFreelancer);

            return missionRepository.save(mission);
        }).orElseThrow(() -> new ResourceNotFoundException("Mission with Id= " +idMission+ " not found  "));

    }

    @GetMapping("/getAppliedMissionsToFreelancer/{idFreelancer}") //get the list of missions that this freelancer is invited for
    public  List<Optional>  getAppliedMissionsToFreelancer(@PathVariable Long idFreelancer)
    {
        List<Optional> listMissions=new ArrayList<Optional>();
        List<List> liste;
        liste=this.missionRepository.getAppliedMissions(idFreelancer);
        //String listeAccepted;
        for (List l : liste)
        {

            if((((String)l.get(1)).indexOf(Long.toString(idFreelancer)))>-1) //test if he is in the list of applied ones for this mission(the mission is not hired)
            {

                    listMissions.add(this.missionRepository.findById((Long) l.get(0)));

            }

        }

        return listMissions;

    }




}
