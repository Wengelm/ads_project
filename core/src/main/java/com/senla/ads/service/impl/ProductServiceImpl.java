package com.senla.ads.service.impl;

import com.senla.ads.entity.Product;
import com.senla.ads.entity.User;
import com.senla.ads.exception.MyEntityNotFoundException;
import com.senla.ads.repository.ProductRepository;
import com.senla.ads.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Value("${product.priceForOneDay}")
    private Float priceForOneDay;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Product save(Product product) {
        Float price =  priceForOneDay * product.getCountDays();
        product.setPrice(price);
        return productRepository.save(product);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Product update(Product product) {
        Product p = productRepository.findById(product.getId())
                .orElseThrow(() -> new MyEntityNotFoundException(product.getId()));
        Float price =  priceForOneDay * product.getCountDays();
        p.setPrice(price);
        p.setName(product.getName());
        p.setCountDays(product.getCountDays());
        productRepository.save(p);
        return p;
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new MyEntityNotFoundException(id);
        }

    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
