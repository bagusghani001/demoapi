package com.latihan.demoapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.latihan.demoapi.model.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);

    List<Supplier> findByNameStartsWith(String name);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);

}
