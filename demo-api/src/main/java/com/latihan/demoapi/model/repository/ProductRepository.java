package com.latihan.demoapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.latihan.demoapi.model.entity.Product;
import com.latihan.demoapi.model.entity.Supplier;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product findByNameContains(String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List<Product> findByNameLike(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id= :categoryId")
    public List<Product> findByCategory(@Param("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findBySupplier(@Param("supplier") Supplier supplier);

}
