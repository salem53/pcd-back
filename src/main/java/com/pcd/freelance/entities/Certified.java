package com.pcd.freelance.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="Certified")
public class Certified {
    @EmbeddedId
    private IdCertified idCertified;

    @ManyToOne
    @MapsId("idFreelancer")
    @JoinColumn(name = "freelancer_id")
    Freelancer freelancer;

    @ManyToOne
    @MapsId("idCertification")
    @JoinColumn(name = "certification_id")
    Certification certification;
}
