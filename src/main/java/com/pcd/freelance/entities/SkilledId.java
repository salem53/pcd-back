package com.pcd.freelance.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SkilledId implements Serializable
{
  @Column(name="idFreelancer")
  Long freelancerId;
  @Column(name="idSkill")
  Long skillId;

  public SkilledId(Long freelancerId, Long skillId) {
    this.freelancerId = freelancerId;
    this.skillId = skillId;
  }

  public SkilledId() {
  }

  public Long getFreelancerId() {
    return freelancerId;
  }

  public void setFreelancerId(Long freelancerId) {
    this.freelancerId = freelancerId;
  }

  public Long getSkillId() {
    return skillId;
  }

  public void setSkillId(Long skillId) {
    this.skillId = skillId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SkilledId skilledId = (SkilledId) o;
    return Objects.equals(freelancerId, skilledId.freelancerId) && Objects.equals(skillId, skilledId.skillId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(freelancerId, skillId);
  }
}
