package com.example.devopstest.domain.repository;

import com.example.devopstest.domain.entity.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShopRepository shopRepository;

    private Integer estimatedTime = 30;
    private Boolean deliverFinished = false;
    private String address = "서울시 서초구 삼성빌딩 10층";
    private Long shopId = 1L;
    private LocalDateTime orderAt = LocalDateTime.now();

    @DisplayName("1. create test")
    @Test
    void test1() {
        // given
        Order newOrder = createOrder();

        // when
        Order savedOrder = orderRepository.save(newOrder);

        // then
        Assertions.assertNotNull(savedOrder);
        Assertions.assertEquals(savedOrder.getAddress(), address);
        Assertions.assertEquals(savedOrder.getShop().getId(), shopId);

        System.out.println("test passed!");
    }

    @DisplayName("2. read test")
    @Test
    void test2() {

    }

    private Order createOrder() {
        return Order.builder()
                .estimatedTime(estimatedTime)
                .deliverFinished(deliverFinished)
                .address(address)
                .orderAt(orderAt)
                .shop(shopRepository.getById(shopId))
                .build();
    }
}