package com.sahabuddin.eshoppers.service.impl;

import com.sahabuddin.eshoppers.domain.Product;
import com.sahabuddin.eshoppers.dto.ProductDTO;
import com.sahabuddin.eshoppers.repository.ProductRepository;
import com.sahabuddin.eshoppers.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {
    private static final Product APPLE_I_PAD
            = new Product(1L,"Apple iPad","Apple iPad 10.2 32GB", BigDecimal.valueOf(369.99));
    private static final Product HEADPHONE
            = new Product(2L,"Headphones","Jabra Elite Bluetooth Headphones", BigDecimal.valueOf(249.99));

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void findAllProductSortedByName() {

        when(productRepository.findAllProducts())
                .thenReturn(
                        List.of(HEADPHONE, APPLE_I_PAD)
                );
        var sortedByName = productService.findAllProductSortedByName();
        Assertions.assertEquals(APPLE_I_PAD.getName(),sortedByName.get(0).getName());
        Assertions.assertEquals(HEADPHONE.getName(),sortedByName.get(1).getName());

    }
}