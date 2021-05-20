package com.pcd.freelance.repositories;

import com.pcd.freelance.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByEmail(String clientEmail);

    @Query("SELECT DISTINCT f.nationality FROM Client f ")
    List<String> findNationalities();
}
