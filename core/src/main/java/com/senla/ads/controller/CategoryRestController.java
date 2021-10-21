package com.senla.ads.controller;

import com.senla.ads.dto.CategoryDto;
import com.senla.ads.entity.Category;
import com.senla.ads.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/get/{id}")
    public CategoryDto getCategoryById(@Valid @PathVariable("id") @NotNull @Min(1) Long id) {
        Category category = categoryService.getCategoryById(id);
        return modelMapper.map(category, CategoryDto.class);
    }

    @GetMapping("/all")
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, CategoryDto.class))
                .collect(Collectors.toList());
    }
}
