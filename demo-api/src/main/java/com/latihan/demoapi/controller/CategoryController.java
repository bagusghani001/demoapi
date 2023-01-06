package com.latihan.demoapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.latihan.demoapi.model.dto.CategoryDTO;
import com.latihan.demoapi.model.dto.Response;
import com.latihan.demoapi.model.dto.SearchData;
import com.latihan.demoapi.model.entity.Category;
import com.latihan.demoapi.services.CategoryServices;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<Response<Category>> create(@Valid @RequestBody CategoryDTO categoryDTO, Errors errors) {
        Response<Category> responseData = new Response<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessageError().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryDTO, Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryServices.create(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/list")
    public List<Category> findAll() {
        return categoryServices.findAll();
    }

    @GetMapping("/list/{id}")
    public Category findOne(@PathVariable("id") Long id) {
        return categoryServices.findOne(id);
    }

    @PutMapping("/update")
    public ResponseEntity<Response<Category>> update(@Valid @RequestBody CategoryDTO categoryDTO, Errors errors) {
        Response<Category> responseData = new Response<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessageError().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryDTO, Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryServices.create(category));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/delete/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        categoryServices.removeOne(id);
    }

    @PostMapping("/search/{size}/{page}")
    public Page<Category> findByName(@RequestBody SearchData searchData, @PathVariable("size") int size,
            @PathVariable("page") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryServices.findByName(searchData.getSearchkey(), pageable);
    }

    @PostMapping("/search/{size}/{page}/{sort}")
    public Page<Category> findByName(@RequestBody SearchData searchData, @PathVariable("size") int size,
            @PathVariable("page") int page, @PathVariable("sort") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        if (sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return categoryServices.findByName(searchData.getSearchkey(), pageable);
    }
}
