package com.malifanau.redisprtask.repository;

import com.malifanau.redisprtask.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findByName(String name);
}
