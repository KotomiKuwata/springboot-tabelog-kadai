package com.example.kadai_002.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kadai_002.Detail.MyCustomUserDetails;
import com.example.kadai_002.entity.Review;
import com.example.kadai_002.entity.Store;
import com.example.kadai_002.entity.User;
import com.example.kadai_002.form.ReviewRegisterForm;
import com.example.kadai_002.repository.ReviewRepository;
import com.example.kadai_002.repository.StoreRepository;
import com.example.kadai_002.repository.UserRepository;

@Service
public class ReviewService {
    
    private ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    
    public ReviewService (ReviewRepository reviewRepository, UserRepository userRepository) {
    	this.reviewRepository = reviewRepository;
    	this.userRepository = userRepository;
    }

    @Autowired
    private StoreRepository storeRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review createReview(Review review, Integer storeId, UserDetails userDetails) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Invalid store ID"));
        review.setStore(store);
        review.setUser(((MyCustomUserDetails) userDetails).getUser());
        review.setTimestamp(LocalDateTime.now());
        return reviewRepository.save(review);
    }
    
    @Transactional
    public void create(ReviewRegisterForm reviewRegisterForm) { 
    	Review review = new Review();
        Store store = storeRepository.getReferenceById(reviewRegisterForm.getStoreId());
        User user = userRepository.getReferenceById(reviewRegisterForm.getUserId()); 
        Integer rating = reviewRegisterForm.getRating();
        String comment = reviewRegisterForm.getComment();
                
		review.setStore(store);
		review.setUser(user);
		review.setRating(rating);
		review.setComment(comment);
        
		reviewRepository.save(review);
    }

    public List<Review> getReviewsByStoreId(Integer storeId) {
        return reviewRepository.findByStoreId(storeId);
    }
}
