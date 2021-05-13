package com.pcd.freelance.repositories;


import com.pcd.freelance.entities.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NationalityRepository extends JpaRepository<Nationality,Long> {
    List<Nationality> findByName(String name);
}
