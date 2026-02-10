package com.example.question4_E_commerce_api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductCreation() {
        Product product = new Product(1L, "Test Product", "Description", 99.99, "Category", 10, "Brand");
        
        assertEquals(1L, product.getProductId());
        assertEquals("Test Product", product.getName());
        assertEquals("Description", product.getDescription());
        assertEquals(99.99, product.getPrice());
        assertEquals("Category", product.getCategory());
        assertEquals(10, product.getStockQuantity());
        assertEquals("Brand", product.getBrand());
    }

    @Test
    void testProductSetters() {
        Product product = new Product();
        
        product.setProductId(2L);
        product.setName("Updated Name");
        product.setDescription("Updated Description");
        product.setPrice(199.99);
        product.setCategory("Updated Category");
        product.setStockQuantity(20);
        product.setBrand("Updated Brand");
        
        assertEquals(2L, product.getProductId());
        assertEquals("Updated Name", product.getName());
        assertEquals("Updated Description", product.getDescription());
        assertEquals(199.99, product.getPrice());
        assertEquals("Updated Category", product.getCategory());
        assertEquals(20, product.getStockQuantity());
        assertEquals("Updated Brand", product.getBrand());
    }
}
