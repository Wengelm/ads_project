package com.senla.ads.service;

import com.senla.ads.entity.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    void delete(Long id);

    List<Category> findAll();
}
