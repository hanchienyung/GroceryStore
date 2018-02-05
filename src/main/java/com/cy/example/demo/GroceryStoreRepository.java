package com.cy.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroceryStoreRepository extends CrudRepository<GroceryStore, Long>{
    List<GroceryStore> findAll() ;
}
