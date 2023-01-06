package com.latihan.demoapi.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.latihan.demoapi.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByNameContains(String name, Pageable pageable);
}
