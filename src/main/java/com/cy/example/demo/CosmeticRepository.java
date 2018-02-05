package com.cy.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CosmeticRepository extends CrudRepository<Cosmetic, Long>{
    List<Cosmetic> findAll() ;
}
