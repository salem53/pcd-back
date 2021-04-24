package com.pcd.freelance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name ="Skills",uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Skills {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="idSkill")
  private long id;

  @Column(name="name")
  @NotBlank(message = "Name is mandatory")
  private String name;


  @OneToMany(mappedBy = "skill")
  @JsonIgnore
  Set<PassExamLink> passExams;

  @OneToMany(mappedBy ="skill")
  Set<Skilled> skilled;
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Skills() {
  }

  public Skills(long id, @NotBlank(message = "Name is mandatory") String name) {
    this.id = id;
    this.name = name;

    //this.freelancers = freelancers;
    //this.freelancers = Stream.of(freelancers).collect(Collectors.toSet());
    //this.freelancers.forEach(x -> x.getFreelancers().add(this));
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
