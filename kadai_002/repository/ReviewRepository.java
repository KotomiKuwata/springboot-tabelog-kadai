package com.example.kadai_002.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kadai_002.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAll();
    List<Review> findByStoreId(Integer storeId);
}