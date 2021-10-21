package com.senla.ads.service;

import com.senla.ads.entity.Product;

import java.util.List;

public interface ProductService  {

    Product save(Product product);
    Product update(Product product);
    Product getById(Long id);
    void delete(Long id);
    List<Product> findAll();
}
