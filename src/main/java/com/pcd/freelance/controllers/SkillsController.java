package com.pcd.freelance.controllers;



import com.pcd.freelance.entities.Skills;
import com.pcd.freelance.exception.ResourceNotFoundException;

import com.pcd.freelance.repositories.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/Skills"})
@CrossOrigin("http://localhost:4200")
public class SkillsController {
  @Autowired
  private com.pcd.freelance.repositories.SkillsRepository SkillsRepository;

  public SkillsController(SkillsRepository skillsRepository) {
    this.SkillsRepository = skillsRepository;
  }

  //add new skill
  @PostMapping("/add")
  public Skills createSkills(@Valid @RequestBody Skills skills) {
    return SkillsRepository.save(skills);
  }
  //get list of skills

  @GetMapping("/list")
  public List<Skills> getAllSkills(){
    return SkillsRepository.findAll();
  }

  //get one Skill

  @GetMapping("/{skillId}")
  public java.util.Optional<Skills> getSkills(@PathVariable Long skillId) {
    return  SkillsRepository.findById(skillId);
  }

  //get language by name

  @GetMapping("/getSkillByName/{skillName}")
  public java.util.Optional<Skills> getSkillByName(@PathVariable String skillName) {
    return SkillsRepository.findByName(skillName);
  }
  // update Skill

  @PutMapping("/{skillId}")
  public Skills updateSkill(@PathVariable Long skillId, @Valid @RequestBody Skills skillRequest) {
    return SkillsRepository.findById(skillId).map(skills -> {
      skills.setName(skillRequest.getName());
      return SkillsRepository.save(skills);
    }).orElseThrow(() -> new ResourceNotFoundException("SkillId " + skillId + " not found"));
  }

  // delete Skill
  @DeleteMapping("/{skillId}")
  public ResponseEntity<?> deleteSkill(@PathVariable Long skillId) {
    return SkillsRepository.findById(skillId).map(skill -> {
      SkillsRepository.delete(skill);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("SkillId " +
      skillId + " not found"));
  }
}
