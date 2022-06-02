package com.malifanau.redisprtask.controller;

import com.malifanau.redisprtask.model.Product;
import com.malifanau.redisprtask.service.ProductCacheService;
import com.malifanau.redisprtask.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductCacheService productService;

    @PostMapping
    private ResponseEntity<Product> createProduct(@RequestBody Product product) {
            return ResponseEntity.ok(
                    productService.create(product)
            );
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                productService.getById(id)
        );
    }

    @GetMapping
    private ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(
                productService.getAll()
        );
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Product> updateById(@PathVariable Integer id, @RequestBody Product product) {
        return ResponseEntity.ok(
                productService.updateById(id, product)
        );
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Integer> deleteProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.deleteById(id));
    }

    @GetMapping("/name/{name}")
    private ResponseEntity<List<Product>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    @PostMapping("/clear")
    private ResponseEntity<String> clearCache() {
        productService.clearCache();
        return ResponseEntity.ok(HttpStatus.OK.toString());
    }
}
