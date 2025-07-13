package com.project.WebMart.service;

import com.project.WebMart.model.Products;
import com.project.WebMart.repository.ProductRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){

        this.productRepository = productRepository;
    }
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products addProduct(Products products) {

        return productRepository.save(products);

    }

    public Products getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Optional<Products> updateProduct(int id, Products products) {
        return productRepository.findById(id).map(excisting -> {
            excisting.setDescription(products.getDescription());
            excisting.setPrice(products.getPrice());
            excisting.setAvailability(products.getAvailability());
            return productRepository.save(excisting);
        });
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
