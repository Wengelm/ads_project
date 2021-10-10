package com.senla.ads.service;

import com.senla.ads.entity.Category;

import java.util.List;

public interface CategoryService {


    Category save(Category category);

    Category update(Category category);

    Category getCategoryById(Long id);

    void delete(Long id);

    List<Category> findAll();

}
