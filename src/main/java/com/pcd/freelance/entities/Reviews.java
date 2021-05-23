package com.pcd.freelance.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "Reviews")
public class Reviews {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name ="id")
    private Long id;

    @Column(name ="commentFreelancer")
    private String commentFreelancer="";
    @Column(name ="commentClient")
    private String commentClient="";

    @ManyToOne
    @JoinColumn(name ="idFreelancer")
    private Freelancer freelancer;

    @ManyToOne
    @JoinColumn(name ="idClient")
    private Client client;
    @OneToOne
    @JoinColumn(name = "idMission")
    private Mission mission;

    public Reviews(String commentFreelancer, String commentClient, Freelancer freelancerComment, Client clientComment) {
        this.id=this.mission.getId();
        this.commentFreelancer = commentFreelancer;
        this.commentClient = commentClient;
        this.freelancer = freelancerComment;
        this.client = clientComment;
    }

    public Reviews() {
    }

    public Reviews(String commentFreelancer, String commentClient, Freelancer freelancer, Client client, Mission mission) {
        this.id=this.mission.getId();
        this.commentFreelancer = commentFreelancer;
        this.commentClient = commentClient;
        this.freelancer = freelancer;
        this.client = client;
        this.mission = mission;
    }

    public Reviews(Freelancer freelancer, Client client, Mission mission) {
        this.id=this.mission.getId();
        this.freelancer = freelancer;
        this.client = client;
        this.mission = mission;
    }

    public Reviews(Long id, String commentFreelancer, String commentClient, Freelancer freelancer, Client client, Mission mission) {
        this.id=this.mission.getId();
        this.commentFreelancer = commentFreelancer;
        this.commentClient = commentClient;
        this.freelancer = freelancer;
        this.client = client;
        this.mission = mission;
    }
}
