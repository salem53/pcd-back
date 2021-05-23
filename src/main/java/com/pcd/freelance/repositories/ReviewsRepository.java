package com.pcd.freelance.repositories;


import com.pcd.freelance.entities.Mission;
import com.pcd.freelance.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Reviews,Long> {
    @Query("SELECT review from Reviews  review  WHERE review.freelancer.id=?1 AND review.commentFreelancer=?2 ")
    List<Reviews> findEmptyReviewsOfFreelancer(Long idfreelancer,String commentFreelancer);
    @Query("SELECT review from Reviews  review  WHERE review.client.id=?1 AND review.commentClient=?2 ")
    List<Reviews> findEmptyReviewsOfClient(Long idClient,String clientComment);

    @Query("UPDATE Reviews r SET r.commentFreelancer= ?1  WHERE r.client.id= ?3 and r.freelancer.id=?2 ")
    Reviews addFreelancerReview(String review, Long idFreelancer, Long idClient);
    @Query("SELECT review from Reviews  review  WHERE review.client.id=?1 AND review.freelancer.id=?1 ")
    Optional<Object> findByIdClientAndFreelancer(Long idFreelancer, Long idClient);

    @Query("SELECT review.id from Reviews  review  WHERE review.client.id=?2 AND review.freelancer.id=?1 ")
     Long getIdOfReviews(Long idFreelancer, Long idClient);

    @Query("SELECT review from Reviews  review  WHERE  review.freelancer.id=?1 ")
    List<Reviews> findReviewsAboutFreelancer(Long idFreelancer);
}
