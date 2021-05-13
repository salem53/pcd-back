package com.pcd.freelance.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Studies")
public class Study {
  @EmbeddedId
  StudyId Id;
  @ManyToOne
  @MapsId("IdFreelancer")
  @JoinColumn(name="idFreelancer")
  Freelancer freelancer;

  @ManyToOne
  @MapsId("IdEducation")
  @JoinColumn(name="idEducation")
  Education education;

  @Column(name="beginningDate")
  Date beginningDate;
  @Column(name="endDate")
  Date endDate;
  @Column(name="description")
  String description;
  @Column(name="result")
  String result;

  public Study(StudyId id, Freelancer freelancer, Education education, Date beginningDate, Date endDate, String description, String result) {
    Id = id;
    this.freelancer = freelancer;
    this.education = education;
    this.beginningDate = beginningDate;
    this.endDate = endDate;
    this.description = description;
    this.result= result;
  }

  public Study() {
  }

  public StudyId getId() {
    return Id;
  }

  public void setId(StudyId id) {
    Id = id;
  }

  public Freelancer getFreelancer() {
    return freelancer;
  }

  public void setFreelancer(Freelancer freelancer) {
    this.freelancer = freelancer;
  }

  public Education getEducation() {
    return education;
  }

  public void setEducation(Education education) {
    this.education = education;
  }

  public Date getBeginningDate() {
    return beginningDate;
  }

  public void setBeginningDate(Date beginningDate) {
    this.beginningDate = beginningDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
