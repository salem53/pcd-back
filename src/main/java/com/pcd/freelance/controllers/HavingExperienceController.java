package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.*;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.ExperienceRepository;
import com.pcd.freelance.repositories.FreelancerRepository;
import com.pcd.freelance.repositories.HavingExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping({"/HavingExperience"})
//@CrossOrigin("http://localhost:4200")
public class HavingExperienceController {
    @Autowired
    private HavingExperienceRepository HExperienceRepository;
    //private FreelancerRepository fr=new;
   // private ExperienceRepository er;
    FreelancerController fr=new FreelancerController();
    ExperienceController ex=new ExperienceController();
    public HavingExperienceController(HavingExperienceRepository HExperienceRepository) {
        this.HExperienceRepository = HExperienceRepository;
    }

    //add an experience for the freelancer
   /* @PostMapping("/addHavingExperience/{freelancerId}/{experienceId}")
    public HavingExperience createExperience(@Valid @RequestBody nouveau n, @PathVariable Long freelancerId, @PathVariable Long experienceId  )
    {

//        Optional<Freelancer> f= fr.findById(freelancerId);
//        Optional<Experience> e=er.findById(experienceId);
//        Hexperience.setExperience(e.get());
//        Hexperience.setFreelancer(f.get());
       // Hexperience.setIdHavingExperience(new IdHavingExperience(freelancerId,experienceId));
     //   return HExperienceRepository.save(Hexperience);
       /* Freelancer f=fr.getAFreelancer(freelancerId).get();
        Experience e=ex.getAnExp(experienceId).get();
        */

     /*   HavingExperience h=new HavingExperience(new IdHavingExperience(freelancerId,experienceId),n.getDescription(),n.getJobType(),n.getBeginingDate(),n.getEndingDate());
        return HExperienceRepository.save(h);
    }
    */

    @DeleteMapping("/deleteHavingExperience/{idFreelancer}/{idExp}")
    public ResponseEntity<?> deleteHavingExperience(@PathVariable Long idFreelancer,@PathVariable Long idExp)
    {
        return HExperienceRepository.findById(new IdHavingExperience(idFreelancer,idExp)).map(HavingExperience->{
            HExperienceRepository.delete(HavingExperience);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Having Experience with id =(" +
                idFreelancer+"," +idExp+ ") not found"));
    }
    @GetMapping("getHavingExperienceById/{idFreelancer}/{idExp}")
    public HavingExperience getHavingExperienceById(@PathVariable Long idFreelancer,@PathVariable Long idExp)
    {
        return HExperienceRepository.findById(new IdHavingExperience(idFreelancer,idExp)).get();
    }
    @PutMapping("modifyAnExperience/{freelancerId}/{experienceId}")
    public HavingExperience updateFreelancer(@PathVariable Long freelancerId,@PathVariable Long experienceId, @Valid @RequestBody nouveau havingExperienceRequest)
    {
        return HExperienceRepository.findById(new IdHavingExperience(freelancerId,experienceId)).map(havingExperience->{
            havingExperience.setDescription(havingExperienceRequest.getDescription());
            havingExperience.setBeginingDate(havingExperienceRequest.getBeginingDate());
            havingExperience.setEndingDate(havingExperienceRequest.getEndingDate());
            havingExperience.setJobType(havingExperienceRequest.getJobType());
            return HExperienceRepository.save(havingExperience);
        }).orElseThrow(() -> new ResourceNotFoundException("Freelancer with Id= " + freelancerId + " not found or experience with id= "+experienceId+" not found "));
    }
    @PostMapping("/addHavingExperience")
    public HavingExperience createExperience(@Valid @RequestBody HavingExperience havingExperience )
    {

//        Optional<Freelancer> f= fr.findById(freelancerId);
//        Optional<Experience> e=er.findById(experienceId);
//        Hexperience.setExperience(e.get());
//        Hexperience.setFreelancer(f.get());
        // Hexperience.setIdHavingExperience(new IdHavingExperience(freelancerId,experienceId));
        //   return HExperienceRepository.save(Hexperience);
       /* Freelancer f=fr.getAFreelancer(freelancerId).get();
        Experience e=ex.getAnExp(experienceId).get();
        */

    //  HavingExperience h=new HavingExperience(new IdHavingExperience(freelancerId,experienceId),n.getDescription(),n.getJobType(),n.getBeginingDate(),n.getEndingDate());
        return HExperienceRepository.save(havingExperience);
    }

}

