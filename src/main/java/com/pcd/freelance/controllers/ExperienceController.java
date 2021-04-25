package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.Experience;

import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping({"/experience"})
@CrossOrigin("http://localhost:4200")
public class ExperienceController {
@Autowired
    private ExperienceRepository experienceRepository;

    public ExperienceController(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    //add a  experience for the freelancer
    @PostMapping("/addExperience")
        public Experience createExperience(@Valid @RequestBody Experience experience)
    {
        return experienceRepository.save(experience);
    }
    public java.util.Optional<Experience> getAnExp(Long expId) {
        return experienceRepository.findById(expId);
    }

    public ExperienceController() {
    }
}


