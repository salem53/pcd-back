package com.pcd.freelance.entities;

import javax.persistence.*;

@Entity
@Table(name="Certification")
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public Certification() {
    }

    public Certification(String name, String organism) {
        this.name = name;
        this.organism = organism;
    }

    @Column(name="name")
    private String name;
    @Column(name="organism")
    private String organism;
}
