package com.pcd.freelance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="Education")
public class Education {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="id")
  long id;
  @Column(name="school")
  String school;
  @Column(name="degree")
  String degree;

  @JsonIgnore
  @OneToMany(mappedBy = "education")
  Set<Study> studies;


  public Education( String school, String degree) {

    this.school = school;
    this.degree = degree;
  }

  public Education() {
  }

  public Education(long id) {
    this.id = id;
  }
}
