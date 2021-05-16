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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/HavingExperience"})
@CrossOrigin("http://localhost:4200")
public class HavingExperienceController {
    @Autowired
    private HavingExperienceRepository HExperienceRepository;

    public HavingExperienceController(HavingExperienceRepository HExperienceRepository) {
        this.HExperienceRepository = HExperienceRepository;
    }


    @DeleteMapping("/deleteHavingExperience/{idFreelancer}/{idExp}")
    public ResponseEntity<?> deleteHavingExperience(@PathVariable Long idFreelancer,@PathVariable Long idExp)
    {
        return HExperienceRepository.findById(new IdHavingExperience(idFreelancer,idExp)).map(HavingExperience->{
            HExperienceRepository.delete(HavingExperience);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Having Experience with id =(" +
                idFreelancer+"," +idExp+ ") not found"));
    }
    @GetMapping("/getHavingExperienceById/{idFreelancer}/{idExp}")
    public HavingExperience getHavingExperienceById(@PathVariable Long idFreelancer,@PathVariable Long idExp)
    {
        return HExperienceRepository.findById(new IdHavingExperience(idFreelancer,idExp)).get();
    }
    @PutMapping("/modifyAnExperience")
    public HavingExperience updateFreelancer(@Valid @RequestBody HavingExperience havingExperienceRequest)
    {
        return HExperienceRepository.findById(new IdHavingExperience(havingExperienceRequest.getFreelancer().getId(),havingExperienceRequest.getExperience().getId())).map(havingExperience->{
            havingExperience.setDescription(havingExperienceRequest.getDescription());
            havingExperience.setBeginingDate(havingExperienceRequest.getBeginingDate());
            havingExperience.setEndingDate(havingExperienceRequest.getEndingDate());
            havingExperience.setJobType(havingExperienceRequest.getJobType());
            return HExperienceRepository.save(havingExperience);
        }).orElseThrow(() -> new ResourceNotFoundException("Freelancer with Id= " + havingExperienceRequest.getFreelancer().getId() + " not found or experience with id= "+havingExperienceRequest.getExperience().getId()+" not found "));
    }
    @PostMapping("/addHavingExperience")
    public HavingExperience createExperience(@Valid @RequestBody HavingExperience havingExperience )
    {


        return HExperienceRepository.save(havingExperience);
    }

    @GetMapping("/listByIdFreelancer/{idFreelancer}") //return the educational experiences of a certain freelancer
    public List<HavingExperience> getAllExperiencesOfFreelancer(@PathVariable Long idFreelancer){
        return HExperienceRepository.findAllExperiencesByIdFreelancer( idFreelancer);
    }


}

