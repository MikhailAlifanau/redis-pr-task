package com.malifanau.redisprtask.service.impl;

import com.malifanau.redisprtask.exception.ProductNotFoundException;
import com.malifanau.redisprtask.model.Product;
import com.malifanau.redisprtask.repository.ProductRepository;
import com.malifanau.redisprtask.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@CacheConfig(cacheNames = Product.CACHE_NAME)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @CachePut(key = "#product.id")
    @CacheEvict(allEntries = true)
    public Product create(Product product) {
        log.info("Repository is saving product {}", product);
        return productRepository.save(product);
    }

    @Cacheable
    public List<Product> getAll() {
        log.info("Repository is fetching all products");
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        sleep(1);
        return products;
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public Product getById(Integer id) {
        log.info("Repository is retrieving product by id {}", id);
        sleep(1);
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @CachePut(key = "#id")
    @CacheEvict(allEntries = true)
    public Product updateById(Integer id, Product product) {
        log.info("Repository is updating product by id {}, {}", id, product);
        Product target = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        target.setName(product.getName());
        target.setPrice(product.getPrice());
        return productRepository.save(target);
    }

    @CacheEvict(allEntries = true)
    public Integer deleteById(Integer id) {
        log.info("Repository is deleting product by id {}", id);
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
        return id;
    }

    @Override
    @Cacheable
    public List<Product> findByName(String name) {
        log.info("Repository is retrieving product by name {}", name);
        sleep(1);
        return productRepository.findByNameIgnoreCase(name);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void clearCache() {

    }


    private void sleep(long seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException ignored) {
        }
    }
}
