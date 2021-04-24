package com.pcd.freelance.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class SkilledId implements Serializable
{
  @Column(name="idFreelancer")
  Long freelancerId;
  @Column(name="idSkill")
  Long skillId;

  public SkilledId(Long freelancerId, Long skillId) {
    this.freelancerId = freelancerId;
    this.skillId = skillId;
  }

  public SkilledId() {
  }

  public Long getFreelancerId() {
    return freelancerId;
  }

  public void setFreelancerId(Long freelancerId) {
    this.freelancerId = freelancerId;
  }

  public Long getSkillId() {
    return skillId;
  }

  public void setSkillId(Long skillId) {
    this.skillId = skillId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SkilledId skilledId = (SkilledId) o;
    return Objects.equals(freelancerId, skilledId.freelancerId) && Objects.equals(skillId, skilledId.skillId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(freelancerId, skillId);
  }
}
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

  @Column(name="score")
  float score;

  public Skilled() {
  }

  public Skilled(SkilledId id, Freelancer freelancer, Skills skill, float score) {
    this.id = id;
    this.freelancer = freelancer;
    this.skill = skill;
    this.score = score;
  }

  public SkilledId getId() {
    return id;
  }

  public void setId(SkilledId id) {
    this.id = id;
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

  public float getScore() {
    return score;
  }

  public void setScore(float score) {
    this.score = score;
  }
}
