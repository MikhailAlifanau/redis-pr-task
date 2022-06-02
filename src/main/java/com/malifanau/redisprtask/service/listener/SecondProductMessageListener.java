package com.malifanau.redisprtask.service.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malifanau.redisprtask.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SecondProductMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product;
        try {
            product = objectMapper.readValue(message.getBody(), Product.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("2nd listener consumed {}", product);
    }
}
