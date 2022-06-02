package com.malifanau.redisprtask.service;

import com.malifanau.redisprtask.model.Product;
import com.malifanau.redisprtask.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductCacheService implements ProductService {

    private final ProductServiceImpl productService;

    public Product create(Product product) {
        return productService.create(product);
    }

    public List<Product> getAll() {
        return productService.getAll();
    }

    public Product getById(Integer id) {
        return productService.getById(id);
    }

    public Product updateById(Integer id, Product product) {
        return productService.updateById(id, product);
    }

    public Integer deleteById(Integer id) {
        return productService.deleteById(id);
    }

    public List<Product> findByName(String name) {
        return productService.findByName(name);
    }

    public void clearCache() {
        productService.clearCache();
    }
}
