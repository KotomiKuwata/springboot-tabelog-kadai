package com.example.kadai_002.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kadai_002.entity.Review;
import com.example.kadai_002.entity.Store;
import com.example.kadai_002.entity.User;
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
	

	public void validateReview(Review review) {
		if (review.getRating() == null || review.getRating() < 1 || review.getRating() > 5) {
			throw new IllegalArgumentException("評価は1から5の間で入力してください。");
		}

		if (review.getComment() == null || review.getComment().trim().isEmpty()) {
			throw new IllegalArgumentException("コメントを入力してください。");
		}

		if (review.getComment().length() > 200) {
			throw new IllegalArgumentException("コメントは200文字以内で入力してください。");
		}
		
	}
}

/* 

 
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
    
     public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

   
    
    public List<Review> getReviewsByStoreId(Integer storeId) {
        return reviewRepository.findByStoreId(storeId);
    }
    
*/