package com.latihan.demoapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.latihan.demoapi.model.entity.Category;
import com.latihan.demoapi.model.repository.CategoryRepository;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category findOne(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            return null;
        } else {
            return category.get();
        }
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void removeOne(Long id) {
        categoryRepository.deleteById(id);
    }

    public Page<Category> findByName(String name, Pageable pageable) {

        return categoryRepository.findByNameContains(name, pageable);
    }
}
