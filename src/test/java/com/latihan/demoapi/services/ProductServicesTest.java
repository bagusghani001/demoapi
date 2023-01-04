package com.latihan.demoapi.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.latihan.demoapi.model.entity.Product;
import com.latihan.demoapi.model.repository.ProductRepository;

@SpringBootTest
public class ProductServicesTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void testAddSupplier() {

    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setDescription("testing");
        product.setPrice(0);
        product.setCategory(null);
        productRepository.save(product);
        assertNotNull(productRepository.findById(1L));

    }

    @Test
    void testFindAll() {

    }

    @Test
    void testRemoveOne() {

    }
}
