package com.pcd.freelance.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name="HavingExperience")
public class HavingExperience {


    @EmbeddedId
    private IdHavingExperience idHavingExperience;

    @ManyToOne
    @MapsId("idFreelancer")
    @JoinColumn(name = "freelancer_id")
    Freelancer freelancer;

    @ManyToOne
    @MapsId("idExperience")
    @JoinColumn(name = "experience_id")
    Experience experience;

    @Column(name="description")
    private String description;
    @Column(name="jobType")
    private String jobType;
    @Column(name = "beginingDate")
    private Date beginingDate;
    @Column(name = "endingDate")

    private Date endingDate;

    public HavingExperience() {
    }
    public HavingExperience(Freelancer freelancer, Experience experience) {
        this.freelancer = freelancer;
        this.experience = experience;
    }

    public HavingExperience(IdHavingExperience idHavingExperience, Freelancer freelancer, Experience experience) {
        this.idHavingExperience = idHavingExperience;
        this.freelancer = freelancer;
        this.experience = experience;
    }
    public HavingExperience(IdHavingExperience idHavingExperience, Freelancer freelancer, Experience experience, String description, String jobType, Date beginingDate, Date endingDate) {
        this.idHavingExperience = idHavingExperience;
        this.freelancer = freelancer;
        this.experience = experience;
        this.description = description;
        this.jobType = jobType;
        this.beginingDate = beginingDate;
        this.endingDate = endingDate;
    }

    public HavingExperience(Freelancer freelancer, Experience experience, String description, String jobType, Date beginingDate, Date endingDate) {
        this.freelancer = freelancer;
        this.experience = experience;
        this.description = description;
        this.jobType = jobType;
        this.beginingDate = beginingDate;
        this.endingDate = endingDate;
    }

    public HavingExperience(String description, String jobType, Date beginingDate, Date endingDate) {
        this.description = description;
        this.jobType = jobType;
        this.beginingDate = beginingDate;
        this.endingDate = endingDate;
       // this.idHavingExperience=new IdHavingExperience(idFreelancer,idExperience);
    }

}

