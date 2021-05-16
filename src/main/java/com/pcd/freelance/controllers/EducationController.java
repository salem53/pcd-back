package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.Education;
import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.entities.Language;
import com.pcd.freelance.repositories.EducationRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Education")
@CrossOrigin("http://localhost:4200")
public class EducationController {
  private EducationRepository educationRepository;

  public EducationController(EducationRepository educationRepository) {
    this.educationRepository = educationRepository;
  }
  @PostMapping("/addEducation")
  public Education addEducation(@Valid @RequestBody Education education)
  {
    return educationRepository.save(education);
  }


  @GetMapping("/list")
  public List<Education> getAllEducations(){
    return educationRepository.findAll();
  }

  //get one Language

  @GetMapping("/getEducation/{educationId}")
  public java.util.Optional<Education> getLanguage(@PathVariable Long educationId) {
    return  educationRepository.findById(educationId);

  }
  @GetMapping("/getEducationBySchoolAndDegree/{school}/{degree}")
  public List<Education> getLanguage(@PathVariable String school,@PathVariable String degree) {
    return  educationRepository.findBySchoolAndDegree(school,degree);

  }

  @GetMapping("/listById")
  public Optional<Education> getAllFreelancers(Long id){
    return educationRepository.findById(id);
  }



}
