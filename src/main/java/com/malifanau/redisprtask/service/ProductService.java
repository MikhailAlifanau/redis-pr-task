package com.malifanau.redisprtask.service;

import com.malifanau.redisprtask.model.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product);

    List<Product> getAll();

    Product getById(Integer id);

    Product updateById(Integer id, Product product);

    Integer deleteById(Integer id);

    List<Product> findByName(String name);

    void clearCache();
}
