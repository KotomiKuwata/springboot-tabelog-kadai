package com.example.kadai_002.service;

import org.springframework.stereotype.Service;

import com.example.kadai_002.entity.Category;
import com.example.kadai_002.form.CategoryEditForm;
import com.example.kadai_002.form.CategoryRegisterForm;
import com.example.kadai_002.repository.CategoryRepository;

import jakarta.transaction.Transactional;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;    
    
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;        
    }    
    
    @Transactional
    public void create(CategoryRegisterForm categoryRegisterForm) {
        Category category = new Category();        
      
        
        
        category.setName(categoryRegisterForm.getName());                
                    
        categoryRepository.save(category);
    }
    
    @Transactional
    public void update(CategoryEditForm categoryEditForm) {
    	Category category = categoryRepository.getReferenceById(categoryEditForm.getId());
  

        category.setName(categoryEditForm.getName());                
        
        categoryRepository.save(category);
    }
    
}
