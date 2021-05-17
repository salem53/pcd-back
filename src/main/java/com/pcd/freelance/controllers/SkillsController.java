package com.pcd.freelance.controllers;



import com.pcd.freelance.entities.Skills;

import com.pcd.freelance.repositories.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/Skills"})
@CrossOrigin("http://localhost:4200")
public class SkillsController {
  @Autowired
  private SkillsRepository skillsRepository;

  public SkillsController(SkillsRepository skillsRepository) {
    this.skillsRepository = skillsRepository;
  }

  //add new skill
 /* @PostMapping("/add")
  public Skills createSkills(@Valid @RequestBody Skills skill) throws FileNotFoundException {
    if(skill.getFileContent()!=null) {
      String name = skill.getFileContent().getName();
      String nameFile = skill.getFile();
      int index = name.lastIndexOf(".");
      List<Question> ExamQuestion = new ArrayList<Question>();
      ObjectMapper objectMapper = new ObjectMapper();
      TypeReference<List<Question>> typeReference = new TypeReference<List<Question>>() {
      };

      if ((name.substring(index + 1, name.length()).equals("json")) && (skill.getNbQuestion() != 0)) {
        if (!(skill.getFileContent().exists()) && (SkillsRepository.findByName(skill.getName()) == null)) {
          File newFile = new File("src/main/resources/Exams/" + name);

          try (var is = new FileInputStream(skill.getFile())) {
            Files.copy(is, newFile.toPath());
            InputStream inputStream = TypeReference.class.getResourceAsStream("src/main/resources/Exams/" + name);
            List<Question> questions = objectMapper.readValue(inputStream, typeReference);

            List<Integer> numberOfQuestion = new ArrayList<Integer>();
            SkillsRepository.save(skill);
          } catch (JsonParseException e) {
            e.printStackTrace();
          } catch (JsonMappingException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      } else {
        System.out.println("a json file should be uploaded ");
      }
    }
    else
    {
      return SkillsRepository.save(skill);
    }

  }
  //get list of skills*/
/*
  @GetMapping("/list")
  public List<Skills> getAllSkills(){
    return SkillsRepository.findAll();
  }

  //get one Skill

  @GetMapping("/{skillId}")
  public java.util.Optional<Skills> getSkills(@PathVariable Long skillId) {
    return  SkillsRepository.findById(skillId);
  }



  @GetMapping("/getSkillByName/{skillName}")
  public java.util.Optional<Skills> getSkillByName(@PathVariable String skillName) {
    return SkillsRepository.findByName(skillName);
  }
  // update Skill

  @PutMapping("/{skillId}")
  public Skills updateSkill(@PathVariable Long skillId, @Valid @RequestBody Skills skillRequest) {
    return SkillsRepository.findById(skillId).map(skills -> {
      skills.setName(skillRequest.getName());
      //skills.setFileContent(skillRequest.getFileContent());
      skills.setNbQuestion(skillRequest.getNbQuestion());
      //skills.setScore(skillRequest.getScore());
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
*/
  @PostMapping("/addSkill")
  public Skills createExperience(@RequestBody Skills skillRequest)
  {
    return skillsRepository.save(skillRequest);
  }

  @GetMapping("/getSkillByName")
  public List<Skills> getSkillByName(@Valid @PathVariable String name)
  {
    return skillsRepository.findByName(name);
  }

}
