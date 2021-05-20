package com.pcd.freelance.entities;

import com.pcd.freelance.entities.Freelancer;
import com.pcd.freelance.entities.Skills;
import lombok.Data;

import java.util.ArrayList;

@Data
public class FreelancerWithSkills {

    Freelancer freelancer;
    ArrayList<String> skills;
}
