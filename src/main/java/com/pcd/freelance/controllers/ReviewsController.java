package com.pcd.freelance.controllers;


import com.pcd.freelance.entities.Mission;
import com.pcd.freelance.entities.Reviews;
import com.pcd.freelance.repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/Reviews")
@CrossOrigin("http://localhost:4200")
public class ReviewsController {
    @Autowired
    private ReviewsRepository reviewsRepository;

    @PostMapping("/addReviews")
    public Reviews createReviews(@Valid @RequestBody Reviews reviewsRequest)
    {
        System.out.println(reviewsRequest);
        return reviewsRepository.save(reviewsRequest);
    }
}
