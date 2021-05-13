package com.pcd.freelance.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
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

    @Column(name="date")
    private Date date;
    @Column(name = "url")
    private String url;
    @Column(name = "file")
    private String file;

    public Certified(IdCertified idCertified, Freelancer freelancer, Certification certification) {
        this.idCertified = idCertified;
        this.freelancer = freelancer;
        this.certification = certification;
    }

    public Certified() {
    }

    public Certified(Freelancer freelancer, Certification certification) {
        this.freelancer = freelancer;
        this.certification = certification;
    }

    public Certified(IdCertified idCertified) {
        this.idCertified = idCertified;
    }

    public Certified(IdCertified idCertified, Freelancer freelancer, Certification certification, Date date, String url, String file) {
        this.idCertified = idCertified;
        this.freelancer = freelancer;
        this.certification = certification;
        this.date = date;
        this.url = url;
        this.file = file;
    }

    public Certified(Freelancer freelancer, Certification certification, Date date, String url, String file) {
        this.freelancer = freelancer;
        this.certification = certification;
        this.date = date;
        this.url = url;
        this.file = file;
    }
}
