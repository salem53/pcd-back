package com.pcd.freelance.entities;

import lombok.Data;

import java.util.List;

@Data
public class MissionNotHiredWithApplicationsAndInvitedAndAcceptedInvitations {
    Mission mission;
    List<Freelancer> invitedFreelancers;
    List<Freelancer> freelancersAcceptedInvitations;
    List<Freelancer> appliedFreelancers;

}
