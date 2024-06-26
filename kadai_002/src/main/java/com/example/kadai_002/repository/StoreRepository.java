package com.example.kadai_002.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kadai_002.entity.Store;
public interface StoreRepository extends JpaRepository<Store, Integer> {

}