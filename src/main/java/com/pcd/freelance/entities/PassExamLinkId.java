package com.pcd.freelance.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PassExamLinkId implements Serializable {
  @Column(name="Exam_id")
  Long ExamId;

  @Column(name="Skill_id")
  Long SkillId;

  public PassExamLinkId(Long examId, Long skillId) {
    ExamId = examId;
    SkillId = skillId;
  }

  public PassExamLinkId() {
  }

  public Long getExamId() {
    return ExamId;
  }

  public void setExamId(Long examId) {
    ExamId = examId;
  }

  public Long getSkillId() {
    return SkillId;
  }

  public void setSkillId(Long skillId) {
    SkillId = skillId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PassExamLinkId that = (PassExamLinkId) o;
    return Objects.equals(ExamId, that.ExamId) && Objects.equals(SkillId, that.SkillId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ExamId, SkillId);
  }
}
