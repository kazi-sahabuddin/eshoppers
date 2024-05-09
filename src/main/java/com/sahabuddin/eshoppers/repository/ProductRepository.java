package com.sahabuddin.eshoppers.repository;

import com.sahabuddin.eshoppers.domain.Product;


import java.util.List;

public interface ProductRepository {
    List<Product> findAllProducts();
}
