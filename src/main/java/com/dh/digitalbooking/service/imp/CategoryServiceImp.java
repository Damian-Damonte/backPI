package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.category.CategoryFullDto;
import com.dh.digitalbooking.dto.category.CategoryRequest;
import com.dh.digitalbooking.entity.Category;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.mapper.CategoryMapper;
import com.dh.digitalbooking.repository.CategoryRepository;
import com.dh.digitalbooking.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImp(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryFullDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::categoryToCategoryFullDto).toList();
    }

    @Override
    public CategoryFullDto getCategoryById(Long id) {
        return categoryMapper.categoryToCategoryFullDto(existByIdValidation(id));
    }

    @Override
    @Transactional
    public CategoryFullDto saveCategory(CategoryRequest categoryRequest) {
        String name = categoryRequest.name();
        if (categoryRepository.findByName(name).isPresent())
            throw new BadRequestException("There is already a category with the name '" + name + "'");
        Category category = categoryRepository.save(Category.builder()
                        .name(categoryRequest.name())
                        .description(categoryRequest.description())
                        .imageUrl(categoryRequest.imageUrl())
                        .productsCount(0)
                        .build());
        return categoryMapper.categoryToCategoryFullDto(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = existByIdValidation(id);
        if (!(category.getProducts().isEmpty()))
            throw new BadRequestException("You cannot delete the category with id " + id + " because there are products registered in that category");

        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CategoryFullDto updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = existByIdValidation(id);
        String updateName = categoryRequest.name();
        Category categoryByName = categoryRepository.findByName(updateName).orElse(null);
        if (categoryByName != null && !(Objects.equals(categoryByName.getId(), id)))
            throw new BadRequestException("There is already a category with the name '" + updateName + "'");
        category.setName(updateName);
        category.setDescription(categoryRequest.description());
        category.setImageUrl(categoryRequest.imageUrl());
        return categoryMapper.categoryToCategoryFullDto(category);
    }

    @Override
    public void incrementCount(Long id) {
        categoryRepository.incrementCount(id);
    }

    @Override
    public void decrementCount(Long id) {
        categoryRepository.decrementCount(id);
    }

    public Category existByIdValidation(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Category with id " + id + " not found"));
    }
}
