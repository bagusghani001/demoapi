package com.latihan.demoapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import com.latihan.demoapi.model.dto.SupplierDTO;
import com.latihan.demoapi.model.entity.Product;
import com.latihan.demoapi.model.entity.Supplier;
import com.latihan.demoapi.services.SupplierServices;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierServices supplierServices;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<Response<Supplier>> create(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors) {
        Response<Supplier> responseData = new Response<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessageError().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierServices.create(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/list")
    public List<Supplier> findAll() {
        return supplierServices.findAll();
    }

    @GetMapping("/list/{id}")
    public Supplier findOne(@PathVariable("id") Long id) {
        return supplierServices.findOne(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Response<Supplier>> update(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors) {
        Response<Supplier> responseData = new Response<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessageError().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierServices.create(supplier));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/delete/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        supplierServices.removeOne(id);
    }

    @PostMapping("/addProduct/{id}")
    public void addProduct(@RequestBody Product product, @PathVariable("id") Long supplierId) {
        supplierServices.addProduct(product, supplierId);
    }

    @PostMapping("/findByEmail")
    public Supplier findByEmail(@RequestBody SearchName searhName) {
        return supplierServices.findByEmail(searhName.getEmail());
    }

    @PostMapping("/findByName")
    public List<Supplier> findByName(@RequestBody SearchName searhName) {
        return supplierServices.findByName(searhName.getName());
    }

    @PostMapping("/findByNameStartWith")
    public List<Supplier> findByStartWith(@RequestBody SearchName searhName) {
        return supplierServices.findByNameStartsWith(searhName.getName());
    }

    @PostMapping("/findByNameOrEmail")
    public List<Supplier> findByNameOrEmail(@RequestBody SearchName searhName) {
        return supplierServices.findByNameContainsOrEmailContains(searhName.getName(), searhName.getEmail());
    }

}
