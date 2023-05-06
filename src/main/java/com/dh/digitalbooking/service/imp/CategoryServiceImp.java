package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.entity.Category;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.repository.CategoryRepository;
import com.dh.digitalbooking.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return existByIdValidation(id);
    }

    @Override
    @Transactional
    public Category saveCategory(Category category) {
        String name = category.getName();

        if (categoryRepository.findByName(name).isPresent())
            throw new BadRequestException("There is already a category with the name '" + name + "'");
        category.setProductsCount(0);

        return categoryRepository.save(category);
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
    public Category updateCategory(Category updateCategory) {
        Long id = updateCategory.getId();
        Category category = existByIdValidation(id);
        String updateName = updateCategory.getName();

        Category categoryByTitulo = categoryRepository.findByName(updateName).orElse(null);
        if (categoryByTitulo != null && !(Objects.equals(categoryByTitulo.getId(), id)))
            throw new BadRequestException("There is already a category with the name '" + updateName + "'");
// sacar el .save, usar la @Transactional
        updateCategory.setProductsCount(category.getProductsCount());
        return categoryRepository.save(updateCategory);
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
