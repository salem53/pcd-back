package com.pcd.freelance.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity @Data
@Table(name ="administrateur",uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class Admin {

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

    public Admin(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
