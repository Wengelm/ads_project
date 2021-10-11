package com.senla.ads.controller;

import com.senla.ads.dto.CategoryDto;
import com.senla.ads.entity.Category;
import com.senla.ads.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/admin/category")
@Validated
public class AdminCategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryService.save(category);
        return modelMapper.map(category, CategoryDto.class);
    }

    @DeleteMapping("delete/{id}")
    public String deleteCategory(@PathVariable("id") @NotNull @Min(1) Long id) {
        categoryService.delete(id);
        return "successful deleted";
    }

    @PutMapping("/update")
    public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryService.update(category);
        return modelMapper.map(category, CategoryDto.class);
    }
}
