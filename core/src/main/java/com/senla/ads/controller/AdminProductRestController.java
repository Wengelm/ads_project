package com.senla.ads.controller;

import com.senla.ads.dto.ProductDto;
import com.senla.ads.dto.request.EntityIdRequest;
import com.senla.ads.entity.Product;
import com.senla.ads.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/product")
@SecurityRequirement(name = "bearerAuth")
public class AdminProductRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/add")
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productService.save(product);
        return modelMapper.map(product, ProductDto.class);
    }

    @PutMapping("/update")
    public ProductDto update(@RequestBody ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productService.update(product);
        return modelMapper.map(product, ProductDto.class);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody EntityIdRequest entityIdRequest) {
        productService.delete(entityIdRequest.getId());
        return "successfully deleted";
    }




}
