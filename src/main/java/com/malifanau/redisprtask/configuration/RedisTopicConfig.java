package com.malifanau.redisprtask.configuration;

import com.malifanau.redisprtask.service.listener.ProductMessageListener;
import com.malifanau.redisprtask.service.listener.RedissonListener;
import com.malifanau.redisprtask.service.listener.SecondProductMessageListener;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisTopicConfig {

    @Value("${product.queue.name}")
    private String productTopic;
    @Value("${customer.queue.name}")
    private String customerTopic;

    @Bean
    public RedisTemplate<String, String> redisTopicTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic(productTopic);
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new ProductMessageListener(), topic());
        container.addMessageListener(new SecondProductMessageListener(), topic());
        return container;
    }

    /**
     * redisson topic config
     */
    @Bean
    public RTopic rTopic(RedissonClient redissonClient) {
        RTopic topic = redissonClient.getTopic(customerTopic);
        topic.addListener(String.class, new RedissonListener());
        return topic;
    }
}
