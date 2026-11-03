package com.microservice.product.service;

import com.microservice.product.entity.Product;
import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(Long id);
}
