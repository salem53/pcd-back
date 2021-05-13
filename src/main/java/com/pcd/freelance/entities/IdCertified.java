package com.pcd.freelance.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdCertified implements Serializable  {
    @Column(name="id_certification")
    private Long idCertification;
    @Column(name="id_freelancer")
    private Long idFreelancer;

    public Long getIdCertification() {
        return idCertification;
    }

    public void setIdCertification(Long idCertification) {
        this.idCertification = idCertification;
    }

    public IdCertified() {
    }

    public Long getIdFreelancer() {
        return idFreelancer;
    }

    public void setIdFreelancer(Long idFreelancer) {
        this.idFreelancer = idFreelancer;
    }

    public IdCertified(Long idCertification, Long idFreelancer) {
        this.idCertification = idCertification;
        this.idFreelancer = idFreelancer;
    }
}
