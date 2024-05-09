package com.sahabuddin.eshoppers.service.impl;

import com.sahabuddin.eshoppers.domain.Product;
import com.sahabuddin.eshoppers.dto.ProductDTO;
import com.sahabuddin.eshoppers.repository.ProductRepository;
import com.sahabuddin.eshoppers.service.ProductService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAllProductSortedByName() {

        return productRepository.findAllProducts()
                .stream()
                .map(this::convertToDTO)
                .sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
