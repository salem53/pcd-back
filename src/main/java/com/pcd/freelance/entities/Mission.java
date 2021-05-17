package com.pcd.freelance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Missions")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name ="id")
    private Long id;
    @Column(name ="hired")
    private String hired;
    @Column(name ="completed")
    private  String completed;
    @Column(name ="title")
    private String title;
    @Column(name ="technologies")
    private String technologies;
    @Column(name ="description")
    private String description;
    @Column(name ="averagePayment")
    private Integer averagePayment;
    @Column(name ="duration")
    private Integer duration;
    @Column(name ="beginningDate")
    private Date beginningDate;
    @Column(name ="contrat")
    private String contrat;
    @Column(name ="invited")
    private String invited ;
    @Column(name ="filePath")
    private String filePath ;

    @ManyToOne
    @JoinColumn(name ="idFreelancer")
    private Freelancer freelancer;


    @ManyToOne
    @JoinColumn(name ="idClient")
    private Client client;



    public Mission() {
    }




    public Mission(String title, String technologies, String description, Integer averagePayment, Integer duration, Date beginningDate, String filePath, Client client) {
        this.title = title;
        this.technologies = technologies;
        this.description = description;
        this.averagePayment = averagePayment;
        this.duration = duration;
        this.beginningDate = beginningDate;
        this.filePath = filePath;
        this.client = client;
        this.invited = "false";
    }

    public Mission(String hired, String completed, String title, String technologies, String description, Integer averagePayment, Integer duration, Date beginningDate, String contrat, String invited, String filePath, Freelancer freelancer, Client client) {
        this.hired = hired;
        this.completed = completed;
        this.title = title;
        this.technologies = technologies;
        this.description = description;
        this.averagePayment = averagePayment;
        this.duration = duration;
        this.beginningDate = beginningDate;
        this.contrat = contrat;
        this.invited = invited;
        this.filePath = filePath;
        this.freelancer = freelancer;
        this.client = client;
    }

    public Mission(String hired, String completed, String title, String technologies, String description, Integer averagePayment, Integer duration, Date beginningDate, String contrat, String invited, String filePath, Client client) {
        this.hired = hired;
        this.completed = completed;
        this.title = title;
        this.technologies = technologies;
        this.description = description;
        this.averagePayment = averagePayment;
        this.duration = duration;
        this.beginningDate = beginningDate;
        this.contrat = contrat;
        this.invited = invited;
        this.filePath = filePath;
        this.client = client;
    }

}
