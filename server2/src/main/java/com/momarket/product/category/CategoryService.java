package com.momarket.product.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Method to create a new category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);  // Save the new category
    }
}
