package com.pcd.freelance.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class IdStudy implements Serializable {
  @Column(name="freelancer_id")
  Long idFreelancer;
  @Column(name="education_id")
  Long idEducation;

  public IdStudy(Long idFreelancer, Long idEducation) {
    this.idFreelancer = idFreelancer;
    this.idEducation = idEducation;
  }

  public IdStudy() {
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IdStudy idStudy = (IdStudy) o;
    return Objects.equals(idFreelancer, idStudy.idFreelancer) && Objects.equals(idEducation, idStudy.idEducation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idFreelancer, idEducation);
  }
}
