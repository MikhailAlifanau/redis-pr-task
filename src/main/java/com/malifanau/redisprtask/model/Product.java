package com.malifanau.redisprtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@RedisHash("product")
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    private Integer id;
    @Indexed
    private String name;
    private Long price;
    public static final String CACHE_NAME = "product";
}
