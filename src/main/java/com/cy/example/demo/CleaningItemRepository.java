package com.cy.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CleaningItemRepository extends CrudRepository<CleaningItem, Long> {
    List<CleaningItem> findAll() ;
}

