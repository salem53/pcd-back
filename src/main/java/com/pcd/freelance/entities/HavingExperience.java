package com.pcd.freelance.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name="HavingExperience")
public class HavingExperience {
    public HavingExperience(Freelancer freelancer, Experience experience) {
        this.freelancer = freelancer;
        this.experience = experience;
    }

    public HavingExperience(IdHavingExperience idHavingExperience, Freelancer freelancer, Experience experience) {
        this.idHavingExperience = idHavingExperience;
        this.freelancer = freelancer;
        this.experience = experience;
    }

    //    @Id
//    private IdHavingExperience idHavingExperience;
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
   public IdHavingExperience getIdHavingExperience() {
        return idHavingExperience;
    }

    public void setIdHavingExperience(IdHavingExperience idHavingExperience) {
        this.idHavingExperience = idHavingExperience;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Date getBeginingDate() {
        return beginingDate;
    }

    public void setBeginingDate(Date beginingDate) {
        this.beginingDate = beginingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }


}

