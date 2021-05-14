package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.Certified;
import com.pcd.freelance.entities.IdCertified;
import com.pcd.freelance.entities.uploadFile;
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
    public Certified createCertified (@Valid @RequestBody Certified certifRequest,@RequestParam("imageFile") MultipartFile file) throws IOException
    {
       /* FileOutputStream outputStream = null;
        String projectPath = System.getProperty("user.dir") + "/Files/"; //projectPath
        System.out.println(projectPath);
        String filePath = projectPath + file.getOriginalFilename();
        try
        {
            outputStream = new FileOutputStream(new File(filePath));
            outputStream.write(file.getInputStream().read());
            outputStream.close();
            certifRequest.setFile(filePath);
          //  uploadFile img = new uploadFile(file.getOriginalFilename(), file.getContentType(),filePath);

          //  fileRepo.save(img);

        }
        catch (Exception e) {
            System.out.println("Error while saving file");

        }

        finally {
            */
            return certifiedRepository.save(certifRequest);
        //}
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

    @PostMapping("/uploadFile/{idF}/{idC}")
    public void processUpload(@PathVariable("idF") Long idF,@PathVariable Long idC,@RequestParam("imageFile") MultipartFile file) throws IOException {
        FileOutputStream outputStream = null;
        String projectPath = System.getProperty("user.dir") + "/Files/"; //projectPath
        System.out.println(projectPath);
        String filePath = projectPath + file.getOriginalFilename();
        try {
            outputStream = new FileOutputStream(new File(filePath));
            outputStream.write(file.getInputStream().read());
            outputStream.close();
            // uploadFile img = new uploadFile(file.getOriginalFilename(), file.getContentType(),filePath);
            this.certifiedRepository.updateFileById(filePath,idF,idC);
            // fileRepo.save(img);

        } catch (Exception e) {
            System.out.println("Error while saving file");


        }
    }





}