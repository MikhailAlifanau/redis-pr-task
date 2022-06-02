package com.malifanau.redisprtask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malifanau.redisprtask.model.Customer;
import com.malifanau.redisprtask.model.Product;
import com.malifanau.redisprtask.service.CustomerService;
import com.malifanau.redisprtask.service.ProductCacheService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublisherController {

    private final ProductCacheService productService;
    private final RedissonClient redissonClient;
    private final CustomerService customerService;
    private final RedisTemplate<String, String> redisTopicTemplate;

    @Value("${product.queue.name}")
    private String productTopic;

    @Value("${customer.queue.name}")
    private String customerTopic;

    @PostMapping("/product/{id}/publish")
    public String publish(@PathVariable Integer id) {
        Product product = productService.getById(id);
        redisTopicTemplate.convertAndSend(productTopic, product);
        return "Sent product with redis!";
    }

    @PostMapping("/customer/{id}/publish")
    public String publishWithRedisson(@PathVariable Long id) throws JsonProcessingException {
        Customer customer = customerService.getById(id);
        RTopic topic = redissonClient.getTopic(customerTopic);
        ObjectMapper objectMapper = new ObjectMapper();
        topic.publish(objectMapper.writeValueAsString(customer));
        return "Sent customer with redisson!";
    }

}
