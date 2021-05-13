package com.pcd.freelance.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcd.freelance.entities.*;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.FreelancerRepository;
import com.pcd.freelance.repositories.SkilledRepository;
import com.pcd.freelance.repositories.SkillsRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/Skilled")
@CrossOrigin("http://localhost:4200")
public class SkilledController {
  private SkilledRepository skilledRepo;
  private SkillsRepository skillRepo;
  private FreelancerRepository freelancerRepo;
  public SkilledController(SkilledRepository skilledRepo,SkillsRepository skillRepo,FreelancerRepository freelancerRepo) {
    this.skilledRepo = skilledRepo;
    this.freelancerRepo=freelancerRepo;
    this.skillRepo=skillRepo;
  }


  // @GetMapping("/add?{skill}&{freelancerId}")
  @GetMapping("/add/{skill}/{freelancerId}")
  public String addSkilled(@PathVariable("skill") String Skill, @PathVariable("freelancerId") Long IdFreelancer)
  {
    Optional<Skills> s1=skillRepo.findByName(Skill);
    Skills skill=s1.get();
    Optional<Freelancer> f1=freelancerRepo.findById(IdFreelancer);
    Freelancer freelancer=f1.get();
    SkilledId instance=new SkilledId(skill.getId(),IdFreelancer);
    if((skilledRepo.findById(instance))!=null)
    {
      return "this skill is already stored in your list";
    }
    else
    {
        Skilled newSkilled=new Skilled(instance,freelancer,skill,0,0);
        skilledRepo.save(newSkilled);
        return "Skill saved successfully";
    }
  }

  @GetMapping("PassExam/{freelancerId}/{skill}")
  public List<Question> generateQuestions(@PathVariable Long freelancerId,@PathVariable String skill ) {
    //create ObjectMapper instance
    //transform all the json fields into question objects and put randomly nbQuestion in a list to generate the exam
    ObjectMapper objectMapper = new ObjectMapper();
    TypeReference<List<Question>> typeReference = new TypeReference<List<Question>>() {
    };
    Optional<Skills> s1=skillRepo.findByName(skill);
    Skills skill1=s1.get();
    List<Question> ExamQuestion = new ArrayList<Question>() ;
    InputStream inputStream = TypeReference.class.getResourceAsStream( skill1.getFile() );
    try {
      List<Question> questions = objectMapper.readValue(inputStream, typeReference);

      List<Integer> numberOfQuestion = new ArrayList<Integer>();


      int i=0;
      while(i<skill1.getNbQuestion())
      {
        Random R=new Random();
        int j= R.nextInt(questions.size()+1);
        if(!(numberOfQuestion.contains(j))) {
          ExamQuestion.add(questions.get(j));
          numberOfQuestion.add(j);
          i++;
        }
      }

    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      return ExamQuestion;
    }

  }

  @PutMapping("/update/{freelancerId}/{skill}/{result}")
  public Skilled updateResult(@PathVariable Long freelancerId,@PathVariable String skill,@PathVariable int result)
  {
    Freelancer freelancer=freelancerRepo.findById(freelancerId).get();
    Skills skill1= skillRepo.findByName(skill).get();
    SkilledId skilledId=new SkilledId(freelancerId,skill1.getId());
    Skilled SkilledRequest=skilledRepo.findById(skilledId).get();
    AtomicInteger AncienNombreEssai= new AtomicInteger(SkilledRequest.getNbEssai());
    return skilledRepo.findById(skilledId).map(skilled -> {
      skilled.setResult(result);
      skilled.setNbEssai(AncienNombreEssai.getAndIncrement());
        if (skilled.getResult() > skill1.getScore()) {
          skilled.setValidate(true);
        }
      return skilledRepo.save(skilled);
      }
      ).orElseThrow(() -> new ResourceNotFoundException("SkillId " + skilledId.toString() + " not found"));
  }


  @GetMapping("/generateCertification/{freelancerId}/{skill}")
  public void generateCertification(@PathVariable Long freelancerId,@PathVariable String skill) {
    Skills skill1 = skillRepo.findByName(skill).get();
    Freelancer freelancer = freelancerRepo.findById(freelancerId).get();
    SkilledId Id = new SkilledId(freelancerId, skill1.getId());
    Skilled skilled = skilledRepo.findById(Id).get();
    if (skilled.getResult() > skill1.getScore()) {
      skilled.setValidate(true);
    }
  }
  }
