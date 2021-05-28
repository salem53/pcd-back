package com.pcd.freelance.controllers;


import com.pcd.freelance.entities.Mission;
import com.pcd.freelance.entities.Reviews;
import com.pcd.freelance.exception.ResourceNotFoundException;
import com.pcd.freelance.repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Reviews")
@CrossOrigin("http://localhost:4200")
public class ReviewsController {
    @Autowired
    private ReviewsRepository reviewsRepository;

    @PostMapping("/add")
    public Reviews createReviews(@Valid @RequestBody Reviews reviewsRequest)
    {
        //System.out.println(reviewsRequest);
        reviewsRequest.setId(reviewsRequest.getMission().getId());
        System.out.println(reviewsRequest.getId());
        return reviewsRepository.save(reviewsRequest);
    }
    @GetMapping("/getEmptyReviewsofFreelancer/{idFreelancer}")
    public List<Reviews> getEmptyReviewsOfFreelancer(@PathVariable Long idFreelancer)
    {
        return reviewsRepository.findEmptyReviewsOfFreelancer(idFreelancer,"");
    }
    @PutMapping("/addFreelancerReview/{idFreelancer}/{idClient}")
    public Reviews createReviews(@Valid @RequestBody Reviews reviewRequest,@PathVariable Long idFreelancer,@PathVariable Long idClient)
    {
        System.out.println(reviewRequest);
        Long id=reviewsRepository.getIdOfReviews(idFreelancer,idClient);

        return reviewsRepository.findById(id).map(review->{
            review.setCommentFreelancer(reviewRequest.getCommentFreelancer());
            return reviewsRepository.save(review);
        }).orElseThrow(() -> new ResourceNotFoundException("Mission with Id=  not found  "));
    }
        //return the client's reviews about this freelancer
    @GetMapping("/getReviewsAboutFreelancer/{idFreelancer}")
    public List<Reviews> getReviewsAboutFreelancer(@PathVariable Long idFreelancer)
    {
        return reviewsRepository.findReviewsAboutFreelancer(idFreelancer);
    }

    //return the freelancer's reviews about this client
    @GetMapping("/getReviewsAboutClient/{idClient}")
    public List<Reviews> getReviewsAboutClient(@PathVariable Long idClient)
    {
        return reviewsRepository.findReviewsAboutClient(idClient);
    }
}
