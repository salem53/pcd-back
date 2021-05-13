package com.pcd.freelance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name ="Language",uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Language {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="idLanguage")
  private long id;

  @Column(name="name")
  @NotBlank(message = "Name is mandatory")
  private String name;

  @OneToMany(mappedBy = "language")
  Set<Speak> freelancers;

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

  public Language() {
  }

  public Language(@NotBlank(message = "Name is mandatory") String name) {

    this.name = name;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
