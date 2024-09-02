package com.example.kadai_002.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kadai_002.entity.Review;
import com.example.kadai_002.form.ReviewRegisterForm;
import com.example.kadai_002.repository.ReviewRepository;
import com.example.kadai_002.repository.StoreRepository;
import com.example.kadai_002.repository.UserRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, StoreRepository storeRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Transactional
    public void create(ReviewRegisterForm reviewRegisterForm) {
        Review review = new Review();
        //Store store = storeRepository.getReferenceById(reviewRegisterForm.getStoreId());
        //User user = userRepository.getReferenceById(reviewRegisterForm.getUserId());
        Integer rating = reviewRegisterForm.getRating();
        String comment = reviewRegisterForm.getComment();

        review.setRating(rating);
        review.setComment(comment);
        review.setTimestamp(LocalDateTime.now());

        reviewRepository.save(review);
    }
    
    public List<Review> getReviewsByStoreId(Integer storeId) {
        return reviewRepository.findByStoreId(storeId);
    }
    
    

}