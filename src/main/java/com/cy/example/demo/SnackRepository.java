package com.cy.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SnackRepository extends CrudRepository<Snack, Long>{
    List<Snack> findAll() ;
}
