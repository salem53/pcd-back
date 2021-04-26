package com.pcd.freelance.controllers;
import com.pcd.freelance.repositories.ExamRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcd.freelance.entities.Exam;

import com.pcd.freelance.entities.Question;
import com.pcd.freelance.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping({"/Exam"})
@CrossOrigin("http://localhost:4200")
public class ExamController {
  @Autowired
  private ExamRepository ExamRepository;

  public ExamController(ExamRepository ExamRepository) {
    this.ExamRepository = ExamRepository;
  }

  //add new exam
  @PostMapping("/add")
  public Exam createExam(@Valid @RequestBody Exam Exam) throws FileNotFoundException {
    String name = Exam.getFileContent().getName();
    int index = name.lastIndexOf(".");
    if (name.substring(index + 1, name.length()).equals("json")) {
      if(!(Exam.getFileContent().exists())) {
        File newFile = new File("src/main/resources/Exams/" + Exam.getFileContent().getName());
        try(var is=new FileInputStream(Exam.getFile()))
        {
          Files.copy(is,newFile.toPath());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } else {
      System.out.println("a json file should be uploaded ");
    }
    return ExamRepository.save(Exam);
  }

  //get list of Exams

  @GetMapping("/list")
  public List<Exam> getAllExams() {
    return ExamRepository.findAll();
  }
  //get one Exam referencing a skill

  @GetMapping("/{SkillName}")
  public Optional<Exam> getExam(@PathVariable String SkillName) throws IOException {
    return ExamRepository.findByFile(SkillName+".json");
  }
  //generate list of random questions for an exam
  @GetMapping("/GenerateExam/{SkillName}")
  public List<Question> generateQuestions(@PathVariable String SkillName) {
    //create ObjectMapper instance
    //transform all the json fields into question objects and put randomly nbQuestion in a list to generate the exam
    ObjectMapper objectMapper = new ObjectMapper();
    TypeReference<List<Question>> typeReference = new TypeReference<List<Question>>() {
    };
    List<Question> ExamQuestion = new ArrayList<Question>() ;
    InputStream inputStream = TypeReference.class.getResourceAsStream("src/main/resources/Exams" + SkillName + ".json");
    try {
      List<Question> questions = objectMapper.readValue(inputStream, typeReference);

      List<Integer> numberOfQuestion = new ArrayList<Integer>();
      Exam Exam=ExamRepository.findByFileContent(SkillName+".json");

      int i=0;
      while(i<Exam.getNbQuestion())
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
  // update Exam

  @PutMapping("/{skillName}")
  public Exam updateExam(@PathVariable String skillName, @Valid @RequestBody Exam ExamRequest) {
     return ExamRepository.findByFile(skillName+".json").map(exam -> {
      exam.setFileContent(ExamRequest.getFileContent());
      exam.setNbQuestion(ExamRequest.getNbQuestion());
      return ExamRepository.save(exam);

    }).orElseThrow(() -> new ResourceNotFoundException("" + skillName+ " not found"));

  }

  // delete freelancer
  @DeleteMapping("/{skillName}")
  public ResponseEntity<?> deleteExam(@PathVariable String skillName) {
    return ExamRepository.findByFile(skillName+".json").map(exam -> {
      ExamRepository.delete(exam);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("SkillName " +
      skillName+ " not found"));
  }

}
