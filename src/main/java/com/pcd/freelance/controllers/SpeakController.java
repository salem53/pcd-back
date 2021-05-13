package com.pcd.freelance.controllers;

import com.pcd.freelance.entities.*;
import com.pcd.freelance.repositories.FreelancerRepository;
import com.pcd.freelance.repositories.LanguageRepository;
import com.pcd.freelance.repositories.SpeakRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/speakedLanguages")
@CrossOrigin("http://localhost:4200")
public class SpeakController {
  private SpeakRepository speakRepository;
  private LanguageRepository languageRepository;
  private FreelancerRepository freelancerRepository;

  public SpeakController(SpeakRepository speakRepository, LanguageRepository languageRepository, FreelancerRepository freelancerRepository) {
    this.speakRepository = speakRepository;
    this.languageRepository = languageRepository;
    this.freelancerRepository = freelancerRepository;
  }

  public SpeakController() {
  }

  @GetMapping("/addLanguage/{idFreelancer}/{Language}")
  public void addLanguageSpeaked(@PathVariable Long idFreelancer , @PathVariable String Language)
  {
    Language language=languageRepository.findByName(Language).get();
    Freelancer freelancer=freelancerRepository.findById(idFreelancer).get();
    SpeakId Id=new SpeakId(freelancer.getId(),language.getId());
    Speak newSpeakedLanguage=new Speak(Id,freelancer,language);
    speakRepository.save(newSpeakedLanguage);

  }

  @GetMapping("/ListLanguages/{idFreelancer}")
  public List<String> ListLanguagesSpeaked(@PathVariable Long idFreelancer)
  {
    List<String> languageSpeaked=new ArrayList<String>();

    List<Speak> speaks=speakRepository.findAll();
    for(Speak speak:speaks)
    {
      SpeakId Id=speak.getId();
      Long FreelancerId=Id.getFreelancerId();
      if(FreelancerId==idFreelancer)
      {
        Language l=languageRepository.findById(Id.getLanguageId()).get();
         languageSpeaked.add(l.getName());
      }
    }
    return languageSpeaked;
  }


}

