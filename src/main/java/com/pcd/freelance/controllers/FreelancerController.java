package com.pcd.freelance.controllers;

import com.pcd.freelance.encryptDecrypt.AES;
import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@RestController
@RequestMapping({"/freelancers"})
@CrossOrigin("http://localhost:4200")
public class FreelancerController {

    @Autowired
    private FreelancerRepository freelancerRepository;

    public FreelancerController(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    //add freelancer
    @PostMapping("/add")
    public Freelancer createFreelancer(@Valid @RequestBody Freelancer freelancer) {
        freelancer.setPassword(AES.encrypt(freelancer.getPassword(),AES.getPersonalkey()));
        freelancer.setInscriptionDate(new Date());
        return freelancerRepository.save(freelancer);
    }

    //get list of freelancers

    @GetMapping("/list")
    public List<Freelancer> getAllFreelancers(){
        return freelancerRepository.findAll();
    }

    //get one freelancer

    @GetMapping("/{freelancerId}")
    public java.util.Optional<Freelancer> getFreelancer(@PathVariable Long freelancerId) {
        return  freelancerRepository.findById(freelancerId);
    }

   /* public java.util.Optional<Freelancer> getAFreelancer( Long freelancerId) {
        return  freelancerRepository.findById(freelancerId);
    }
*/
    //get freelancer by email

    @GetMapping("/getFreelancerByEmail/{freelancerEmail}")
    public Freelancer getFreelancerByEmail(@PathVariable String freelancerEmail) {
        Freelancer freelancer = freelancerRepository.findByEmail(freelancerEmail).get();
        freelancer.setPassword(AES.decrypt(freelancer.getPassword(),AES.getPersonalkey()));
        return freelancer;
    }

    // update freelancer

    @PutMapping("/{freelancerId}")
    public Freelancer updateFreelancer(@PathVariable Long freelancerId, @Valid @RequestBody Freelancer freelancerRequest) {
        return freelancerRepository.findById(freelancerId).map(freelancer -> {
            freelancer.setFirstName(freelancerRequest.getFirstName());
            freelancer.setLastName(freelancerRequest.getLastName());
            freelancer.setEmail(freelancerRequest.getEmail());
            freelancer.setAddress(freelancerRequest.getAddress());
            freelancer.setBirthday(freelancerRequest.getBirthday());
            freelancer.setSexe(freelancerRequest.getSexe());
            freelancer.setTelephoneNumber(freelancerRequest.getTelephoneNumber());
            freelancer.setJob(freelancerRequest.getJob());
            freelancer.setDescription(freelancerRequest.getDescription());
            freelancer.setEarning(freelancerRequest.getEarning());
            return freelancerRepository.save(freelancer);
        }).orElseThrow(() -> new ResourceNotFoundException("FreelancerId " + freelancerId + " not found"));
    }

    // update freelancer profile data

    @PutMapping("/profile/{freelancerEmail}")
    public Freelancer updateFreelancerDataProfil(@PathVariable String freelancerEmail, @Valid @RequestBody Freelancer freelancerRequest) {
        return freelancerRepository.findByEmail(freelancerEmail).map(freelancer -> {
            freelancer.setFirstName(freelancerRequest.getFirstName());
            freelancer.setLastName(freelancerRequest.getLastName());
            freelancer.setEmail(freelancerRequest.getEmail());
            freelancer.setAddress(freelancerRequest.getAddress());
            freelancer.setBirthday(freelancerRequest.getBirthday());
            freelancer.setSexe(freelancerRequest.getSexe());
            freelancer.setTelephoneNumber(freelancerRequest.getTelephoneNumber());
            freelancer.setJob(freelancerRequest.getJob());
            freelancer.setDescription(freelancerRequest.getDescription());
            freelancer.setEarning(freelancerRequest.getEarning());
            freelancer.setNationality(freelancerRequest.getNationality());
            System.out.println(freelancerRequest.getNationality());
            return freelancerRepository.save(freelancer);
        }).orElseThrow(() -> new ResourceNotFoundException("FreelancerId " + freelancerEmail + " not found"));
    }

    // delete freelancer
    @DeleteMapping("/{freelancerId}")
    public ResponseEntity<?> deleteFreelancer(@PathVariable Long freelancerId) {
        return freelancerRepository.findById(freelancerId).map(freelancer -> {
            freelancerRepository.delete(freelancer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("FreelancerId " +
                freelancerId + " not found"));
    }



    public FreelancerController() {
    }

    @PostMapping("/saveImageByEmail/{freelancerEmail}")
    public Freelancer uplaodImage(@RequestParam("imageFile") MultipartFile file,@PathVariable String freelancerEmail) throws IOException, SQLException {
        /*String extension = FilenameUtils.getExtension(file.getOriginalFilename());*/
        System.out.println("Original Image Byte Size - " + file.getBytes().length + " name : "+ file.getOriginalFilename() +
                " type : "+ file.getContentType());

        Freelancer freelancer = freelancerRepository.findByEmail(freelancerEmail).get();
        System.out.println(file);
        if(file!=null) {
            freelancer.setImage(compressBytes(file.getBytes()));
        }


        return freelancerRepository.save(freelancer);
    }
   @GetMapping(path = { "/getImageByEmail/{freelancerEmail}" })

    public Freelancer getImage(@PathVariable String freelancerEmail) throws IOException {


       Freelancer freelancer = freelancerRepository.findByEmail(freelancerEmail).get();

       if(freelancer.getImage()!=null){
           freelancer.setImage(decompressBytes(freelancer.getImage()));
       }





       return freelancer;

    }

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


    public FreelancerController() {
    }

}
