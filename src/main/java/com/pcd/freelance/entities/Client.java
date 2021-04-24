package com.pcd.freelance.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

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


    private String nationality;
    private long imageId;

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Client(String firstName, String lastName, String email, String password, String adress, String sexe, Date birthday, Date inscriptionDate, double rating, String telephoneNumber, String job, String description, String nationality, long imageId) {
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
        this.nationality = nationality;
        this.imageId = imageId;
    }
}
