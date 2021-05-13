package com.pcd.freelance.controllers;


import com.pcd.freelance.entities.Certification;
import com.pcd.freelance.entities.Experience;
import com.pcd.freelance.repositories.CertificationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Data
@RestController
@RequestMapping({"/certification"})
@CrossOrigin("http://localhost:4200")
public class CertificationController {
    @Autowired
    private CertificationRepository certificationRepository;

    public CertificationController(CertificationRepository certificationRepository) {
        this.certificationRepository = certificationRepository;
    }
    @PostMapping("/addCertification")
    public Certification createCertification ( @Valid @RequestBody Certification certification)
    {
        return certificationRepository.save(certification);
    }
    @GetMapping("/getCertificationByNameAndOrganism/{name}/{organism}")
    public List<Certification> getCertificationByNameAndOrganism(@PathVariable String name, @PathVariable String organism)
    {
        return this.certificationRepository.findByNameAndOrganism(name,organism);
    }
    @GetMapping("/getIdCertification/{name}/{organism}")
    public Long getIdExperience(@PathVariable String name,@PathVariable String organism)
    {

        return this.getCertificationByNameAndOrganism(name, organism).get(1).getId();

    }

}
