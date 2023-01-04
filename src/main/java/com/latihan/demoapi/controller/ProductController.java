package com.latihan.demoapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latihan.demoapi.model.dto.Response;
import com.latihan.demoapi.model.dto.SearchName;
import com.latihan.demoapi.model.entity.Product;
import com.latihan.demoapi.model.entity.Supplier;
import com.latihan.demoapi.services.ProductServices;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping
    public ResponseEntity<Response<Product>> create(@Valid @RequestBody Product product, Errors errors) {

        Response<Product> responseData = new Response<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessageError().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productServices.create(product));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public List<Product> findAll() {
        return productServices.findAll();
    }

    @GetMapping("/daftar/{id}")
    public Product findOne(@PathVariable("id") Long id) {
        return productServices.findOne(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Response<Product>> update(@Valid @RequestBody Product product, Errors errors) {

        Response<Product> responseData = new Response<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessageError().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productServices.create(product));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/delete/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        productServices.removeOne(id);
    }

    @PostMapping("/findName")
    public Product findByName(@RequestBody SearchName searchName) {
        return productServices.findByName(searchName.getName());
    }

    @PostMapping("/findNameLike")
    public List<Product> findByNameLike(@RequestBody SearchName searchName) {
        return productServices.findByNameLike(searchName.getName());
    }

    @PostMapping("/addSupplier/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId) {
        productServices.addSupplier(supplier, productId);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> findByCategory(@PathVariable("categoryId") Long categoryId) {
        return productServices.findByCategory(categoryId);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<Product> findBySupplier(@PathVariable("supplierId") Long supplierId) {
        return productServices.findBySupplier(supplierId);
    }
}
