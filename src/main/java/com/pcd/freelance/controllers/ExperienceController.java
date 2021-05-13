package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.Experience;

import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping({"/experience"})
@CrossOrigin("http://localhost:4200")
public class ExperienceController {
@Autowired
    private ExperienceRepository experienceRepository;

    public ExperienceController(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }
    public ExperienceController() {
    }

    //add a  experience for the freelancer
    @PostMapping("/addExperience")
        public Experience createExperience(@RequestBody Experience experience)
    {
       // System.out.println("hello");
        return experienceRepository.save(experience);

    }
    @GetMapping("/allExperiences/{company}")
    public List<Experience> getExperienceByCompany(@PathVariable String company)
    {
       return experienceRepository.findByCompany(company);
    }
    @GetMapping("/GetExperiencesByCompanyandPosition/{company}/{position}")
    public List<Experience> getExperienceByCompanyAndPosition(@PathVariable String company,@PathVariable String position)
    {

        return experienceRepository.findByCompanyAndPositionTitle(company,position);
    }
    @GetMapping("/getIdExperience/{company}/{position}")
    public Long getIdExperience(@PathVariable String company,@PathVariable String position)
    {
        System.out.println(this.getExperienceByCompanyAndPosition(company, position).get(1).getId());
        return this.getExperienceByCompanyAndPosition(company, position).get(1).getId();

    }

}


