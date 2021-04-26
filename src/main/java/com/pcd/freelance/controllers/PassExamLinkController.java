package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.*;
import com.pcd.freelance.repositories.PassExamLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/passExam")
@CrossOrigin("http://localhost:4200")
public class PassExamLinkController {

  @Autowired
  private PassExamLinkRepository passExamRepo;

  public PassExamLinkController(PassExamLinkRepository passExamRepo) {
    this.passExamRepo = passExamRepo;
  }

  @PostMapping("/addPassExam")
  public void addExamPassed (@Valid @RequestBody Exam exam, @Valid @RequestBody Skills skill)
  {
    PassExamLinkId link=new PassExamLinkId(exam.getId(), skill.getId());
    PassExamLink examlink=new PassExamLink(link,exam,skill);
    passExamRepo.save(examlink);
  }

  @GetMapping("/get")
  public List<PassExamLink> getAllExams()
  {
    return passExamRepo.findAll();
  }

}
