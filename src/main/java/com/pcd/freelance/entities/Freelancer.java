package com.pcd.freelance.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name ="freelancer",uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class Freelancer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    private long id;
    
    @Column(name ="firstName")
    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @Column(name ="lastName")
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @Column(name ="email",unique = true)
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name ="password")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(name ="address")
    private String adress;

    @Column(name ="sexe")
    private String sexe;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date birthday;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date inscriptionDate;

    private double rating;

    private String telephoneNumber;

    private String job;

    private String description;

    private double earning;

    private long nationalityId;
    private long imageId;

    @OneToMany(mappedBy = "freelancer")
    Set<Mission> missions;

    public Set<Mission> getMissions() {
        return missions;
    }

    public void setMissions(Set<Mission> missions) {
        this.missions = missions;
    }

    //l'association avec experience
    @OneToMany(mappedBy = "freelancer")
    private Set<HavingExperience> experiences; //= new HashSet<HavingExperience>();

    public Set<HavingExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<HavingExperience> experiences) {
        this.experiences = experiences;
    }


    public Freelancer() {
    }

    public Freelancer(long id) {
        this.id = id;
    }

    public Freelancer(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Freelancer(String firstName, String lastName, String email, String password, String adress, String sexe, Date birthday, Date inscriptionDate, double rating, String telephoneNumber, String job, String description, double earning, long nationalityId, long imageId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.adress = adress;
        this.sexe = sexe;
        this.birthday = birthday;
        this.inscriptionDate = inscriptionDate;
        this.rating = rating;
        this.telephoneNumber = telephoneNumber;
        this.job = job;
        this.description = description;
        this.earning = earning;
        this.nationalityId = nationalityId;
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "Freelancer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", adress='" + adress + '\'' +
                ", sexe='" + sexe + '\'' +
                ", birthday=" + birthday +
                ", inscriptionDate=" + inscriptionDate +
                ", rating=" + rating +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", job='" + job + '\'' +
                ", description='" + description + '\'' +
                ", earning=" + earning +
                ", nationalityId=" + nationalityId +
                '}';
    }
}
