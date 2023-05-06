package com.dh.digitalbooking.service;

import com.dh.digitalbooking.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category saveCategory(Category category);
    void deleteCategory(Long id);
    Category updateCategory(Category updateCategory);
    void incrementCount(Long id);
    void decrementCount(Long id);
}
