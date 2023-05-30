package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.category.CategoryFullDto;
import com.dh.digitalbooking.dto.category.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryFullDto> getAllCategories();
    CategoryFullDto getCategoryById(Long id);
    CategoryFullDto saveCategory(CategoryRequest categoryRequest);
    void deleteCategory(Long id);
    CategoryFullDto updateCategory(Long id, CategoryRequest categoryRequest);
    void incrementCount(Long id);
    void decrementCount(Long id);
}
