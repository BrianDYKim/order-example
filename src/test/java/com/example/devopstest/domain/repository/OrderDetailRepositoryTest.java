package com.example.devopstest.domain.repository;

import com.example.devopstest.domain.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderRepository;

    @Autowired
    private ShopRepository shopRepository;

    private String address = "Seoul";
    private int estimatedTime = 10;
    private boolean deliverFinished = false;
    private LocalDateTime orderAt = LocalDateTime.now();
    private Long shopId = 1L;

    @DisplayName("1. create test")
    @Test
    void test1() {
        // given
        OrderDetail newOrderDetail = createOrder();

        // when
        OrderDetail savedOrderDetail = orderRepository.save(newOrderDetail);

        // then
        Assertions.assertNotNull(savedOrderDetail);
        Assertions.assertEquals(savedOrderDetail.getAddress(), address);
        Assertions.assertEquals(savedOrderDetail.getShop().getId(), shopId);

        System.out.println("test passed!");
    }

    @Transactional
    @DisplayName("2. read test")
    @Test
    void test2() {
        // when
        Optional<OrderDetail> foundOrder = orderRepository.findById(2L);

        // then
        Assertions.assertNotNull(foundOrder);
        foundOrder.ifPresent(orderDetail -> {
            Assertions.assertEquals(orderDetail.getAddress(), address);
            Assertions.assertEquals(orderDetail.getShop().getId(), shopId);

            orderDetail.getOrderFoodList().forEach(food -> {
                System.out.println("주문 음식 : " + food.getFoodName());
            });
        });

        System.out.println("test passed!");
    }

    private OrderDetail createOrder() {
        return OrderDetail.builder()
                .address(address)
                .deliverEstimatedTime(estimatedTime)
                .deliverFinished(deliverFinished)
                .orderAt(orderAt)
                .shop(shopRepository.getById(shopId))
                .build();
    }
}