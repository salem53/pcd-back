package com.pcd.freelance.controllers;


import com.pcd.freelance.entities.Language;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/Languages"})
@CrossOrigin("http://localhost:4200")
public class LanguageController {
  @Autowired
  private com.pcd.freelance.repositories.LanguageRepository LanguageRepository;

  public LanguageController(LanguageRepository languageRepository) {
    this.LanguageRepository = languageRepository;
  }

  //add new language
  @PostMapping("/add")
  public Language createLanguage(@Valid @RequestBody Language language) {
    return LanguageRepository.save(language);
  }
  //get list of languages

  @GetMapping("/list")
  public List<Language> getAllFreelancers(){
    return LanguageRepository.findAll();
  }

  //get one Language

  @GetMapping("/{languageId}")
  public java.util.Optional<Language> getLanguage(@PathVariable Long languageId) {
    return  LanguageRepository.findById(languageId);
  }

  //get language by name

  @GetMapping("/getLanguageByName/{languageName}")
  public java.util.Optional<Language> getLanguageByName(@PathVariable String languageName) {
    return LanguageRepository.findByName(languageName);
  }
  // update language

  @PutMapping("/{languageId}")
  public Language updateLanguage(@PathVariable Long languageId, @Valid @RequestBody Language languageRequest) {
    return LanguageRepository.findById(languageId).map(language -> {
      language.setName(languageRequest.getName());
      return LanguageRepository.save(language);
    }).orElseThrow(() -> new ResourceNotFoundException("LanguageId " + languageId + " not found"));
  }

  // delete language
  @DeleteMapping("/{languageId}")
  public ResponseEntity<?> deleteLanguage(@PathVariable Long languageId) {
    return LanguageRepository.findById(languageId).map(language -> {
      LanguageRepository.delete(language);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("LanguageId " +
      languageId + " not found"));
  }
}
