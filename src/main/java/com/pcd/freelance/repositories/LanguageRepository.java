package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Long> {
  Optional<Language> findByName(String languageName);
}
