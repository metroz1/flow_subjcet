package com.example.demo.repository;

import com.example.demo.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {

    boolean existsByName(String name);
    void deleteByName(String name);

    long countAll();
}
