package com.sahabuddin.eshoppers.service;

import com.sahabuddin.eshoppers.domain.Product;
import com.sahabuddin.eshoppers.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProductSortedByName();
}
