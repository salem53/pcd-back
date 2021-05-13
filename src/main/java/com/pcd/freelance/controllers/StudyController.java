package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.*;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.EducationRepository;
import com.pcd.freelance.repositories.FreelancerRepository;
import com.pcd.freelance.repositories.StudyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController("/Studies")
@CrossOrigin("http://localhost:4200")
public class StudyController {
  private StudyRepository studyRepository;
  private EducationRepository educationRepository;
  private FreelancerRepository freelancerRepository;

  public StudyController(StudyRepository studyRepository) {
    this.studyRepository = studyRepository;
  }

  public StudyController() {
  }
  @GetMapping("/add/{idFreelancer}/{idEducation}")
  public void addStudy(@PathVariable Long idFreelancer, @PathVariable Long idEducation, @RequestBody Date beginningDate, @RequestBody Date endDate,@RequestBody String description,@RequestBody String jobType)
  {
    Freelancer freelancer= freelancerRepository.findById(idFreelancer).get();
    Education education=educationRepository.findById(idEducation).get();
    StudyId Id=new StudyId(idFreelancer,idEducation);
    Study Studies=new Study(Id,freelancer,education,beginningDate,endDate,description,jobType);
    studyRepository.save(Studies);

  }

  @GetMapping("/list/{idFreelancer}")
  public List<Study> getStudies(@PathVariable Long idFreelancer)
  {
    List<Study> studies=studyRepository.findAll();
    List<Study> FreelancerStudies=new ArrayList<Study>();
    Iterator<Study> i = studies.iterator();
    while (i.hasNext()) {
      Study s =i.next();
      if(s.getId().getIdFreelancer().equals(idFreelancer))
      {
        FreelancerStudies.add(s);
      }

    }
    return FreelancerStudies;
  }

  @PutMapping("/update/{idFreelancer}/{idEducation}")
  public Study updateStudy(@PathVariable Long idFreelancer, @PathVariable Long idEducation,@RequestBody Study study)
  {
    StudyId Id=new StudyId(idFreelancer,idEducation);
    return studyRepository.findById(Id).map(studies ->
      {
      studies.setDescription(study.getDescription());
      studies.setBeginningDate(study.getBeginningDate());
      studies.setEndDate(study.getEndDate());
      studies.setResult(study.getResult());
      return studyRepository.save(studies);
      }).orElseThrow(() -> new ResourceNotFoundException("Study " + Id.toString() + " not found"));
  }
  @DeleteMapping("/delete/{idFreelancer}/{idEducation}")
  public ResponseEntity<?> deleteStudy(@PathVariable Long idFreelancer,@PathVariable Long idEducation)
  {
    StudyId Id=new StudyId(idFreelancer,idEducation);
    return studyRepository.findById(Id).map(Study -> {
      studyRepository.delete(Study);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("FreelancerId " +
      Id.toString() + " not found"));
  }
  @DeleteMapping("/delete/{idFreelancer}")
  public void deleteStudy(@PathVariable Long idFreelancer)
  {
    List<Study> studies=studyRepository.findAll();
    Iterator<Study> i = studies.iterator();
    while (i.hasNext()) {
      Study s =i.next();
      if(s.getId().getIdFreelancer().equals(idFreelancer))
      {
        studyRepository.delete(s);
      }

    }
  }

  @PostMapping("/addStudy")
  public Study addStudy(@Valid @RequestBody Study study )
  {

    return studyRepository.save(study);

  }
}
