package com.pcd.freelance.entities;

import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
/*
@Entity
@Table(name="PassExam")
public class PassExamLink implements Serializable
{
  private Exam exam;
  private Skills skill ;

  @Id
  @ManyToOne
  @JoinColumn(name="exam_id")
  public Exam getExam() {
    return exam;
  }

  public void setExam(Exam exam) {
    this.exam = exam;
  }

  @Id
  @ManyToOne
  @JoinColumn(name="skill_id")
  public Skills getSkill() {
    return skill;
  }

  public void setSkill(Skills skill) {
    this.skill = skill;
  }

  @Column(name="nbEssai")
  int nbEssai;

  @Column(name="result")
  int result;



  public int getNbEssai() {
    return nbEssai;
  }

  public PassExamLink() {
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

}*/
@Embeddable
class PassExamLinkId implements Serializable {
  @Column(name="Exam_id")
  Long ExamId;

  @Column(name="Skill_id")
  Long SkillId;

  public PassExamLinkId(Long examId, Long skillId) {
    ExamId = examId;
    SkillId = skillId;
  }

  public PassExamLinkId() {
  }

  public Long getExamId() {
    return ExamId;
  }

  public void setExamId(Long examId) {
    ExamId = examId;
  }

  public Long getSkillId() {
    return SkillId;
  }

  public void setSkillId(Long skillId) {
    SkillId = skillId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PassExamLinkId that = (PassExamLinkId) o;
    return Objects.equals(ExamId, that.ExamId) && Objects.equals(SkillId, that.SkillId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ExamId, SkillId);
  }
}

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
