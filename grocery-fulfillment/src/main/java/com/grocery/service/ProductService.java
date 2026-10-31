package com.grocery.service;

import com.grocery.model.Product;
import com.grocery.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product addProduct(Product product) {
        if (product.getName() == null ||
            product.getName().isBlank() ||
            product.getPrice() <= 0) {
            throw new IllegalArgumentException("Invalid product details");
        }

        return repository.save(product);
    }
}