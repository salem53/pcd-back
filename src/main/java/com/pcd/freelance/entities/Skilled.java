package com.pcd.freelance.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="Skilled")
public class Skilled {
  @EmbeddedId
  IdSkilled idSkilled;

  @ManyToOne
  @MapsId("idFreelancer")
  @JoinColumn(name="freelancer_id")
  Freelancer freelancer;

  @ManyToOne
  @MapsId("idSkill")
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


  public Skilled(IdSkilled idSkilled, Freelancer freelancer, Skills skill, int nbEssai, int result, boolean validate) {
    this.idSkilled = idSkilled;
    this.freelancer = freelancer;
    this.skill = skill;
    this.nbEssai = nbEssai;
    this.result = result;
    this.validate = validate;
  }

  public Skilled(Freelancer freelancer, Skills skill) {
    this.freelancer = freelancer;
    this.skill = skill;
  }

  public Skilled(IdSkilled idSkilled, Freelancer freelancer, Skills skill) {
    this.idSkilled = idSkilled;
    this.freelancer = freelancer;
    this.skill = skill;
  }

  public Skilled(Freelancer freelancer, Skills skill, int nbEssai, int result, boolean validate) {
    this.freelancer = freelancer;
    this.skill = skill;
    this.nbEssai = nbEssai;
    this.result = result;
    this.validate = validate;
  }
}
