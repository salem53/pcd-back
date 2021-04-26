package com.pcd.freelance.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class nouveau {
    private String description;
    private String jobType;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date beginingDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date endingDate;

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

    public nouveau(String description, String jobType, Date beginingDate, Date endingDate) {
        this.description = description;
        this.jobType = jobType;
        this.beginingDate = beginingDate;
        this.endingDate = endingDate;
    }

}
