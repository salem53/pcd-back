package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Exam;
import com.pcd.freelance.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam,Long> {
  Exam findByFileContent(String file);

  Optional<Exam> findByFile(String file);
}
