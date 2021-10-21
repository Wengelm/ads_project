package com.senla.ads.controller;

import com.senla.ads.dto.ProductDto;
import com.senla.ads.dto.request.EntityIdRequest;
import com.senla.ads.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@SecurityRequirement(name = "bearerAuth")
public class ProductRestController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;


    @GetMapping("get")
    public ProductDto getProductById(@RequestBody EntityIdRequest entityIdRequest) {
        return modelMapper.map(productService.getById(entityIdRequest.getId()), ProductDto.class);
    }

    @GetMapping("/all")
    public List<ProductDto> findAll() {
        return productService.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, ProductDto.class))
                .collect(Collectors.toList());
    }
}
