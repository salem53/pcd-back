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
@Table(name ="client",uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class Client {

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


    private String nationality;
    @Lob
    @Column(name = "image",nullable = true)
    private byte[] image;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    Set<Mission> missions;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    Set<Reviews> reviews;



    public Set<Mission> getMissions() {
        return missions;
    }

    public void setMissions(Set<Mission> missions) {
        this.missions = missions;
    }

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Client(String firstName, String lastName, String email, String password, String address, String sexe, Date birthday, Date inscriptionDate, double rating, String telephoneNumber, String job, String description, String nationality, byte[] image) {
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
        this.nationality = nationality;
        this.image = image;
    }

    public Client(long id) {

        this.id = id;
    }
}
