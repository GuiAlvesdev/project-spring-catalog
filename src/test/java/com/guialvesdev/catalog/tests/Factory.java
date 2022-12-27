package com.guialvesdev.catalog.tests;

import com.guialvesdev.catalog.dto.ProductDTO;
import com.guialvesdev.catalog.entities.Category;
import com.guialvesdev.catalog.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct(){
        Product product = new Product(1L, "Phone", "Goood Phone", 800.0, "Https://IMG", Instant.parse("2020-07-14T10:00:00Z"));
        product.getCategories().add(new Category(2L, "Electronics"));
        return product;
    }

    public static ProductDTO createProductDTO(){
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }
}
