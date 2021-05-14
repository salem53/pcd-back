package com.pcd.freelance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Education")
public class Education {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="id")
  Long Id;
  @Column(name="School")
  String School;
  @Column(name="degree")
  String degree;

  @OneToMany(mappedBy = "education")
  Set<Study> studies;
  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getSchool() {
    return School;
  }

  public void setSchool(String school) {
    School = school;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public Education( String school, String degree) {

    School = school;
    this.degree = degree;
  }

  public Education() {
  }
}
