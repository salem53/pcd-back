package com.pcd.freelance.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Nationality")
public class Nationality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name ="id")
    private Long id;
   // @Column(name ="name")
    private String name;

    @OneToMany(mappedBy = "nationality")
    @JsonIgnore
    Set<Freelancer> freelancers;

    public Nationality(String name) {
        this.name = name;
    }

    public Nationality(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Nationality() {
    }
}
