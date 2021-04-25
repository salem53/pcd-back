package com.pcd.freelance.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IdHavingExperience implements Serializable
{
   // private static final long serialVersionUID = 1L;
//    @ManyToOne
//    @JoinColumn(name = "Freelancer")
//    Freelancer freelancer;
//    @ManyToOne
//    @JoinColumn(name = "Experience")
//    Experience experience;
//
//    public Freelancer getFreelancer() {
//        return freelancer;
//    }
//
//    public void setFreelancer(Freelancer freelancer) {
//        this.freelancer = freelancer;
//    }
//
//    public Experience getExperience() {
//        return experience;
//    }
//
//    public void setExperience(Experience experience) {
//        this.experience = experience;
//    }

    @Column(name = "freelancer_id")
    Long idFreelancer;

    @Column(name = "experience_id")
    Long idExperience;

    public IdHavingExperience() {
    }

    public IdHavingExperience(Long idFreelancer, Long idExperience) {
        this.idFreelancer = idFreelancer;
        this.idExperience = idExperience;
    }

    public Long getIdFreelancer() {
        return idFreelancer;
    }

    public void setIdFreelancer(Long idFreelancer) {
        this.idFreelancer = idFreelancer;
    }

    public Long getIdExperience() {
        return idExperience;
    }

    public void setIdExperience(Long idExperience) {
        this.idExperience = idExperience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdHavingExperience that = (IdHavingExperience) o;
        return Objects.equals(idFreelancer, that.idFreelancer) && Objects.equals(idExperience, that.idExperience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFreelancer, idExperience);
    }
}

