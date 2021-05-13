package com.pcd.freelance.repositories;


import com.pcd.freelance.entities.uploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepo extends JpaRepository<uploadFile,Long> {
    Optional<uploadFile> findByName(String name);
}
