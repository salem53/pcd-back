package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.*;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.CertifiedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/certified")
@CrossOrigin("http://localhost:4200")
public class CertifiedController {
    @Autowired
    private CertifiedRepository certifiedRepository;

    public CertifiedController(CertifiedRepository certifiedRepository) {
        this.certifiedRepository = certifiedRepository;
    }
    @PostMapping("/addCertified")
    public Certified createCertified (@Valid @RequestBody Certified certifRequest)
    {

            return certifiedRepository.save(certifRequest);

    }
    @DeleteMapping("/deleteCertified/{idFreelancer}/{idCertif}")
    public ResponseEntity<?> deleteCertification(@PathVariable Long idFreelancer,@PathVariable Long idCertif)
    {
        return certifiedRepository.findById(new IdCertified(idFreelancer,idCertif)).map(certified->{
            certifiedRepository.delete(certified);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Certification with id =(" +
                idCertif+"for the freelancer with id" +idFreelancer+ ") not found"));
    }
    @PutMapping("/updateCertified")
    public Certified updateCertification(@Valid @RequestBody Certified certifRequest)
    {
        return certifiedRepository.findById(new IdCertified(certifRequest.getFreelancer().getId(),certifRequest.getCertification().getId())).map(certified->{
           certified.setDate(certifRequest.getDate());
           certified.setFile(certifRequest.getFile());
           certified.setUrl(certifRequest.getUrl());
        return certifiedRepository.save(certified);
        }).orElseThrow(() -> new ResourceNotFoundException("Certification with Id= " + certifRequest.getCertification().getId() + " not found or freelancer with id= "+certifRequest.getFreelancer().getId()+" not found "));
    }

    @GetMapping("/getCertifiedById/{idFreelancer}/{idCertif}")
    public Certified getCertifiedById(@PathVariable Long idFreelancer, @PathVariable Long idCertif)
    {
        return certifiedRepository.findById(new IdCertified(idFreelancer,idCertif)).get();
    }


    @PutMapping("/updateFileCertification/{idFreelancer}/{idCertif}")
    public Certified updateFileCertification (@PathVariable Long idFreelancer,@PathVariable Long idCertif,@RequestParam("imageFile") MultipartFile file) throws IOException
    {
        FileOutputStream outputStream = null;
        String projectPath = System.getProperty("user.dir") + "/CertificationFiles/"; //projectPath
        String filePath = projectPath + file.getOriginalFilename();
        //System.out.println("hello "+idFreelancer+"idCertif "+idCertif);
        try {
            outputStream = new FileOutputStream(new File(filePath));
            outputStream.write(file.getInputStream().read());
            outputStream.close();

        } catch (Exception e) {
            System.out.println("Error while saving file");
        }
        finally {

            return certifiedRepository.findByIdFreelancerAndIdCertification(idFreelancer,idCertif).map(certified->{
                certified.setFile(filePath);
                return certifiedRepository.save(certified);
            }).orElseThrow(() -> new ResourceNotFoundException("certification with IdFreelancer= " +idFreelancer+" and id certification"+ idCertif+" not found  "));

        }


    }

    @GetMapping("/listCertificationByIdFreelancer/{idFreelancer}") //return the educational experiences of a certain freelancer
    public List<Certified> getAllCertificationsOfFreelancer(@PathVariable Long idFreelancer){
        return certifiedRepository.findAllCertificationByIdFreelancer( idFreelancer);
    }





}
