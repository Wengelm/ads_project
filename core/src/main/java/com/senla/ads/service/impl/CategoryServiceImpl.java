package com.senla.ads.service.impl;

import com.senla.ads.entity.Category;
import com.senla.ads.repository.CategoryRepository;
import com.senla.ads.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Category getCategoryById(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Category getCategoryByName(String name) {
        return categoryRepository.getByName(name);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
