package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


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

    //get freelancer by email

    @GetMapping("/getFreelancerByEmail/{freelancerEmail}")
    public java.util.Optional<Freelancer> getFreelancerByEmail(@PathVariable String freelancerEmail) {
        return freelancerRepository.findByEmail(freelancerEmail);
    }

    // update freelancer

    @PutMapping("/{freelancerId}")
    public Freelancer updateFreelancer(@PathVariable Long freelancerId, @Valid @RequestBody Freelancer freelancerRequest) {
        return freelancerRepository.findById(freelancerId).map(freelancer -> {
            freelancer.setFirstName(freelancerRequest.getFirstName());
            freelancer.setLastName(freelancerRequest.getLastName());
            freelancer.setEmail(freelancerRequest.getEmail());
            return freelancerRepository.save(freelancer);
        }).orElseThrow(() -> new ResourceNotFoundException("FreelancerId " + freelancerId + " not found"));
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

}
