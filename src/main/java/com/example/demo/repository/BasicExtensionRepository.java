package com.example.demo.repository;

import com.example.demo.entity.BasicExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicExtensionRepository extends JpaRepository<BasicExtension, Long> {

    boolean existsByName(String name);
    void deleteByName(String name);
}
