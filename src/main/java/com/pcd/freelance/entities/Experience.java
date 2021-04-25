package com.pcd.freelance.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="Experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    @Column(name="positionTitle")
    private String positionTitle;
    @Column(name="company")
    private String company;

    @OneToMany(mappedBy = "experience")
    private Set<HavingExperience> experiences;  //= new HashSet<HavingExperience>();

    public long getId() {
        return id;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getCompany() {
        return company;
    }



   /* public Experience(long id, String positionTitle, String company) {
        this.id = id;
        this.positionTitle = positionTitle;
        this.company = company;
    }
*/
    public void setCompany(String company) {
        this.company = company;
    }

    public Experience() {
    }

    public Experience( String positionTitle, String company) {

        this.positionTitle = positionTitle;
        this.company = company;
    }
}
