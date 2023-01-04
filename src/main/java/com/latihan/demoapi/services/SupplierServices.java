package com.latihan.demoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.latihan.demoapi.model.entity.Product;
import com.latihan.demoapi.model.entity.Supplier;
import com.latihan.demoapi.model.repository.SupplierRepository;

@Service
public class SupplierServices {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier create(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier findOne(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (!supplier.isPresent()) {
            return null;
        } else {
            return supplier.get();
        }
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public void removeOne(Long id) {
        supplierRepository.deleteById(id);
    }

    public void addProduct(Product product, Long supplierId) {
        Supplier supplier = findOne(supplierId);
        if (supplier == null) {
            throw new RuntimeException("Product with ID: " + supplier + " not found");
        }
        supplier.getProducts().add(product);
        create(supplier);
    }

    public Supplier findByEmail(String email) {
        return supplierRepository.findByEmail(email);
    }

    public List<Supplier> findByName(String name) {
        return supplierRepository.findByNameContainsOrderByIdDesc(name);
    }

    public List<Supplier> findByNameStartsWith(String name) {
        return supplierRepository.findByNameStartsWith(name);
    }

    public List<Supplier> findByNameContainsOrEmailContains(String name, String email) {
        return supplierRepository.findByNameContainsOrEmailContains(name, email);
    }
}
