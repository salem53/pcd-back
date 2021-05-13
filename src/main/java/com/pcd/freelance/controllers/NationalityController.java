package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.Certification;
import com.pcd.freelance.entities.Nationality;
import com.pcd.freelance.repositories.CertificationRepository;
import com.pcd.freelance.repositories.NationalityRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Data
@RestController
@RequestMapping({"/nationality"})
@CrossOrigin("http://localhost:4200")
public class NationalityController {

    @Autowired
    private NationalityRepository nationalityRepository;

    public NationalityController(NationalityRepository nationalityRepository) {
        this.nationalityRepository = nationalityRepository;
    }

    @PostMapping("/addNationality")
    public Nationality createNationality (@Valid @RequestBody Nationality nationality)
    {
        return nationalityRepository.save(nationality);
    }
    @GetMapping("/getNationalityByName/{name}")
    public List<Nationality> getNationalityByName(@PathVariable String name)
    {
        return this.nationalityRepository.findByName(name);
    }
//    @GetMapping("/getIdCertification/{name}/{organism}")
//    public Long getIdExperience(@PathVariable String name,@PathVariable String organism)
//    {
//
//        return this.getCertificationByNameAndOrganism(name, organism).get(1).getId();
//
//    }



}
