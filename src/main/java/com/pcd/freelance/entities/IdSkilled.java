package com.pcd.freelance.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class IdSkilled implements Serializable {


    @Column(name = "freelancer_id")
    Long idFreelancer;

    @Column(name="skill_id")
    Long idSkill;

    public IdSkilled(Long idFreelancer, Long idSkill) {
        this.idFreelancer = idFreelancer;
        this.idSkill = idSkill;
    }

    public IdSkilled() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdSkilled idSkilled = (IdSkilled) o;
        return Objects.equals(idFreelancer, idSkilled.idFreelancer) && Objects.equals(idSkill, idSkilled.idSkill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFreelancer, idSkill);
    }
}
