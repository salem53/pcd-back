package com.pcd.freelance.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudyId implements Serializable {
  @Column(name="idFreelancer")
  Long IdFreelancer;
  @Column(name="idEducation")
  Long IdEducation;

  public StudyId(Long idFreelancer, Long idEducation) {
    IdFreelancer = idFreelancer;
    IdEducation = idEducation;
  }

  public StudyId() {
  }

  public Long getIdFreelancer() {
    return IdFreelancer;
  }

  public void setIdFreelancer(Long idFreelancer) {
    IdFreelancer = idFreelancer;
  }

  public Long getIdEducation() {
    return IdEducation;
  }

  public void setIdEducation(Long idEducation) {
    IdEducation = idEducation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StudyId studyId = (StudyId) o;
    return Objects.equals(IdFreelancer, studyId.IdFreelancer) && Objects.equals(IdEducation, studyId.IdEducation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(IdFreelancer, IdEducation);
  }
}
