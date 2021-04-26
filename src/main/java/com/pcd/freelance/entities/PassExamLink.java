package com.pcd.freelance.entities;

import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;



@Entity
@Table(name="PassExam")
public class PassExamLink
{
  @EmbeddedId
  PassExamLinkId id;
  @ManyToOne
  @MapsId("ExamId")
  @JoinColumn(name="exam_id")
  Exam exam;

  @ManyToOne
  @MapsId("SkillId")
  @JoinColumn(name="skill_id")
  Skills skill;

  @Column(name="nbEssai")
  int nbEssai;
  @Column(name="result")
  float result;

  public PassExamLink(PassExamLinkId id, Exam exam, Skills skill, int nbEssai, float result) {
    this.id = id;
    this.exam = exam;
    this.skill = skill;
    this.nbEssai = nbEssai;
    this.result = result;
  }
  public PassExamLink(PassExamLinkId id, Exam exam, Skills skill) {
    this.id = id;
    this.exam = exam;
    this.skill = skill;

  }
  public PassExamLink() {
  }

  public PassExamLinkId getId() {
    return id;
  }

  public void setId(PassExamLinkId id) {
    this.id = id;
  }

  public Exam getExam() {
    return exam;
  }

  public void setExam(Exam exam) {
    this.exam = exam;
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

  public float getResult() {
    return result;
  }

  public void setResult(float result) {
    this.result = result;
  }
}
