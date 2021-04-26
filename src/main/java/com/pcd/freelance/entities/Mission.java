package com.pcd.freelance.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Missions")
public class Mission {
    @Id
    @Column( name ="id")
    private Long id;
    @Column(name ="hired")
    private boolean hired;
    @Column(name ="completed")
    private  boolean completed;
    @Column(name ="title")
    private String title;
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

    @ManyToOne
    @JoinColumn(name ="idFreelancer")
    private Freelancer freelancer;

    @ManyToOne
    @JoinColumn(name ="idClient")
    private Client client;

    public Mission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isHired() {
        return hired;
    }

    public void setHired(boolean hired) {
        this.hired = hired;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAveragePayment() {
        return averagePayment;
    }

    public void setAveragePayment(Integer averagePayment) {
        this.averagePayment = averagePayment;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public String getInvited() {
        return invited;
    }

    public void setInvited(String invited) {
        this.invited = invited;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Mission(Long id, boolean hired, boolean completed, String title, String description, Integer averagePayment, Integer duration, Date beginningDate, String contrat, String invited, Freelancer freelancer, Client client) {
        this.id = id;
        this.hired = hired;
        this.completed = completed;
        this.title = title;
        this.description = description;
        this.averagePayment = averagePayment;
        this.duration = duration;
        this.beginningDate = beginningDate;
        this.contrat = contrat;
        this.invited = invited;
        this.freelancer = freelancer;
        this.client = client;
    }
}
