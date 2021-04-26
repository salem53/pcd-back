package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.PassExamLink;
import com.pcd.freelance.entities.PassExamLinkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassExamLinkRepository extends JpaRepository<PassExamLink, PassExamLinkId> {
}
