package com.sahabuddin.eshoppers.repository.impl;

import com.sahabuddin.eshoppers.domain.Product;
import com.sahabuddin.eshoppers.dto.ProductDTO;
import com.sahabuddin.eshoppers.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

public class DummyProductRepositoryImpl implements ProductRepository {
    private static final List<Product> PRODUCTS = List.of(
            new Product(1L,"Apple iPad","Apple iPad 10.2 32GB", BigDecimal.valueOf(369.99)),
            new Product(2L,"Headphones","Jabra Elite Bluetooth Headphones", BigDecimal.valueOf(249.99)),
            new Product(3L,"Microsoft Surface Pro","Microsoft Surface Pro 7 12.3\" 128GB windows 10", BigDecimal.valueOf(799.99)),
            new Product(4L, "Amazon Echo Dot", "Amazon Echo Dot (3rd Gen) with alex - Charcoal", BigDecimal.valueOf(34.99))
    );
    @Override
    public List<Product> findAllProducts() {
        return PRODUCTS;
    }
}
