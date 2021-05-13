package com.pcd.freelance.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name="Skilled")
public class Skilled {
  @EmbeddedId
  SkilledId id;

  @ManyToOne
  @MapsId("freelancerId")
  @JoinColumn(name="freelancer_id")
  Freelancer freelancer;

  @ManyToOne
  @MapsId("skillId")
  @JoinColumn(name="skill_id")
  Skills skill;

  @Column(name ="NombreEssai")
  int nbEssai;

  @Column(name="result")
  int result;

  @Column(name="Validate")
  boolean validate;
  public Skilled() {
  }

  public boolean isValidate() {
    return validate;
  }

  public void setValidate(boolean validate) {
    this.validate = validate;
  }

  public Skilled(SkilledId id, Freelancer freelancer, Skills skill, int nbEssai, int result) {
    this.id = id;
    this.freelancer = freelancer;
    this.skill = skill;
    this.nbEssai = nbEssai;
    this.result = result;
    this.validate=false;
  }

  public SkilledId getId() {
    return id;
  }


  public Freelancer getFreelancer() {
    return freelancer;
  }

  public void setFreelancer(Freelancer freelancer) {
    this.freelancer = freelancer;
  }

  public Skills getSkill() {
    return skill;
  }

  public void setSkill(Skills skill) {
    this.skill = skill;
  }

  public int getNbEssai() {
    return nbEssai;
  }

  public void setNbEssai(int nbEssai) {
    this.nbEssai = nbEssai;
  }

  public int getResult() {
    return result;
  }

  public void setResult(int result) {
    this.result = result;
  }
}
