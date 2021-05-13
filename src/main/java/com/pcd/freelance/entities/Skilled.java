package com.pcd.freelance.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Data
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



  public Skilled(SkilledId id, Freelancer freelancer, Skills skill, int nbEssai, int result) {
    this.id = id;
    this.freelancer = freelancer;
    this.skill = skill;
    this.nbEssai = nbEssai;
    this.result = result;
    this.validate=false;
  }


}
