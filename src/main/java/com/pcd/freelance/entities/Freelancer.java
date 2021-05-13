package com.pcd.freelance.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


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
    private String address;

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

    private String nationality;
    private long imageId;


  @OneToMany(mappedBy = "freelancer")
    @JsonIgnore
  Set<Skilled> skilled;
<<<<<<< HEAD

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


=======
  @OneToMany(mappedBy = "freelancer")
  @JsonIgnore
  Set<Speak> languages;
  @OneToMany(mappedBy = "freelancer")
  @JsonIgnore
  Set<Study> studies;
>>>>>>> 882d75f2b7318463a10afbbb87d60283d8db8ec8
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

    public Freelancer(String firstName, String lastName, String email, String password, String address, String sexe, Date birthday, Date inscriptionDate, double rating, String telephoneNumber, String job, String description, double earning, String nationality, long imageId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.sexe = sexe;
        this.birthday = birthday;
        this.inscriptionDate = inscriptionDate;
        this.rating = rating;
        this.telephoneNumber = telephoneNumber;
        this.job = job;
        this.description = description;
        this.earning = earning;
        this.nationality = nationality;
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
                ", adress='" + address + '\'' +
                ", sexe='" + sexe + '\'' +
                ", birthday=" + birthday +
                ", inscriptionDate=" + inscriptionDate +
                ", rating=" + rating +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", job='" + job + '\'' +
                ", description='" + description + '\'' +
                ", earning=" + earning +
                ", nationalityId=" + nationality +
                '}';
    }
}
