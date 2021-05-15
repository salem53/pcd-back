package com.pcd.freelance.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="Studies")
public class Study {
  @EmbeddedId
  IdStudy idStudy;

  @ManyToOne
  @MapsId("idFreelancer")
  @JoinColumn(name="freelancer_id")
  Freelancer freelancer;

  @ManyToOne
  @MapsId("idEducation")
  @JoinColumn(name="education_id")
  Education education;

  @Column(name="beginningDate")
  Date beginningDate;
  @Column(name="endDate")
  Date endDate;
  @Column(name="description")
  String description;


  public Study(IdStudy id, Freelancer freelancer, Education education, Date beginningDate, Date endDate, String description) {
    this.idStudy = id;
    this.freelancer = freelancer;
    this.education = education;
    this.beginningDate = beginningDate;
    this.endDate = endDate;
    this.description = description;

  }

  public Study() {
  }

  public Study(IdStudy idStudy, Date beginningDate, Date endDate, String description) {
    this.idStudy = idStudy;
    this.beginningDate = beginningDate;
    this.endDate = endDate;
    this.description = description;

  }

  public Study(Freelancer freelancer, Education education, Date beginningDate, Date endDate, String description) {
    this.freelancer = freelancer;
    this.education = education;
    this.beginningDate = beginningDate;
    this.endDate = endDate;
    this.description = description;

  }

  public Study(IdStudy idStudy, Freelancer freelancer, Education education) {
    this.idStudy = idStudy;
    this.freelancer = freelancer;
    this.education = education;
  }

  public Study(Freelancer freelancer, Education education) {
    this.freelancer = freelancer;
    this.education = education;
  }
}
