package com.malifanau.redisprtask.service.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malifanau.redisprtask.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.listener.MessageListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedissonListener implements MessageListener<String> {

    @Override
    public void onMessage(CharSequence charSequence, String s) {
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer;
        try {
            customer = objectMapper.readValue(s, Customer.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (customer != null) {
            log.info("Received message with redisson {}", customer);
        }
    }
}
