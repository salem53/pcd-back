package com.pcd.freelance.entities;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SpeakId implements Serializable {
  @Column(name="IdFreelancer")
  Long FreelancerId;
  @Column(name="IdLanguage")
  Long LanguageId;

  public SpeakId(Long freelancerId, Long languageId) {
    FreelancerId = freelancerId;
    LanguageId = languageId;
  }

  public SpeakId() {
  }

  public Long getFreelancerId() {
    return FreelancerId;
  }

  public void setFreelancerId(Long freelancerId) {
    FreelancerId = freelancerId;
  }

  public Long getLanguageId() {
    return LanguageId;
  }

  public void setLanguageId(Long languageId) {
    LanguageId = languageId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SpeakId speakId = (SpeakId) o;
    return Objects.equals(FreelancerId, speakId.FreelancerId) && Objects.equals(LanguageId, speakId.LanguageId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(FreelancerId, LanguageId);
  }
}
