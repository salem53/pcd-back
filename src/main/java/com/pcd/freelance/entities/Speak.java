package com.pcd.freelance.entities;

import javax.persistence.*;

@Entity
@Table(name="speak")
public class Speak {
  @EmbeddedId
  SpeakId Id;
  @ManyToOne
  @MapsId("FreelancerId")
  @JoinColumn(name="IdFreelancer")
  Freelancer freelancer;

  @ManyToOne
  @MapsId("LanguageId")
  @JoinColumn(name="IdLanguage")
  Language language;

  public Speak(SpeakId id, Freelancer freelancer, Language language) {
    Id = id;
    this.freelancer = freelancer;
    this.language = language;
  }

  public Speak() {
  }

  public SpeakId getId() {
    return Id;
  }

  public void setId(SpeakId id) {
    Id = id;
  }

  public Freelancer getFreelancer() {
    return freelancer;
  }

  public void setFreelancer(Freelancer freelancer) {
    this.freelancer = freelancer;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }
}
