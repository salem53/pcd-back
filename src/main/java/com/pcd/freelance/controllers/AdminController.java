package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.Admin;
import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/admins"})
@CrossOrigin("http://localhost:4200")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    //add admin
    @PostMapping("/add")
    public Admin createAdmin(@Valid @RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    //get list of admins

    @GetMapping("/list")
    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    //get one admin

    @GetMapping("/{adminId}")
    public java.util.Optional<Admin> getFreelancer(@PathVariable Long adminId) {
        return  adminRepository.findById(adminId);
    }
    //get admin by email

    @GetMapping("/getAdminByEmail/{adminEmail}")
    public java.util.Optional<Admin> getAdminByEmail(@PathVariable String adminEmail) {
        return adminRepository.findByEmail(adminEmail);
    }
    // update admin

    @PutMapping("/{adminId}")
    public Admin updateAdmin(@PathVariable Long adminId, @Valid @RequestBody Admin adminRequest) {
        return adminRepository.findById(adminId).map(admin -> {
            admin.setFirstName(adminRequest.getFirstName());
            admin.setLastName(adminRequest.getLastName());
            admin.setEmail(adminRequest.getEmail());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new ResourceNotFoundException("AdminId " + adminId + " not found"));
    }

    // delete admin
    @DeleteMapping("/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
        return adminRepository.findById(adminId).map(admin -> {
            adminRepository.delete(admin);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("AdminId " +
                adminId + " not found"));
    }
}
