package com.pcd.freelance.repositories;


import com.pcd.freelance.entities.Speak;
import com.pcd.freelance.entities.SpeakId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeakRepository extends JpaRepository<Speak,SpeakId> {
  Optional<Speak> findByFreelancerId(Long Id);
}
