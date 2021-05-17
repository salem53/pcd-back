package com.pcd.freelance.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "Reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name ="id")
    private Long id;

    @Column(name ="commentFreelancer")
    private String commentFreelancer ;
    @Column(name ="commentClient")
    private String commentClient ;

    @ManyToOne
    @JoinColumn(name ="idFreelancer")
    private Freelancer freelancer;

    @ManyToOne
    @JoinColumn(name ="idClient")
    private Client client;

    public Reviews(String commentFreelancer, String commentClient, Freelancer freelancer, Client client) {
        this.commentFreelancer = commentFreelancer;
        this.commentClient = commentClient;
        this.freelancer = freelancer;
        this.client = client;
    }

    public Reviews() {
    }
}
