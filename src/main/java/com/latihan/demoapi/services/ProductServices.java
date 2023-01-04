package com.latihan.demoapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latihan.demoapi.model.entity.Product;
import com.latihan.demoapi.model.entity.Supplier;
import com.latihan.demoapi.model.repository.ProductRepository;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierServices supplierServices;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product findOne(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            return null;
        } else {
            return product.get();
        }
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void removeOne(Long id) {
        productRepository.deleteById(id);
    }

    public Product findByName(String name) {
        return productRepository.findByNameContains(name);
    }

    public List<Product> findByNameLike(String name) {
        return productRepository.findByNameLike("%" + name + "%");
    }

    @Transactional
    public void addSupplier(Supplier supplier, Long productId) {
        Product product = findOne(productId);
        if (product == null) {
            throw new RuntimeException("Product with ID: " + productId + " not found");
        }
        product.getSuppliers().add(supplier);
        create(product);
    }

    public List<Product> findByCategory(Long searchId) {
        return productRepository.findByCategory(searchId);
    }

    public List<Product> findBySupplier(Long supplierId) {
        Supplier supplier = supplierServices.findOne(supplierId);
        if (supplier == null) {
            return new ArrayList<Product>();
        }
        return productRepository.findBySupplier(supplier);
    }

}
