package com.example.devopstest.domain.repository;

import com.example.devopstest.domain.entity.OrderFood;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderFoodRepositoryTest {

    @Autowired
    private OrderFoodRepository orderFoodRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    private String foodName = "존맛 부대찌개";
    private Long orderDetailId = 2L;

    @DisplayName("1. create test")
    @Test
    void test1() {
        // given
        OrderFood newOrderFood = createOrderFood();

        // when
        OrderFood savedOrderFood = orderFoodRepository.save(newOrderFood);

        // then
        Assertions.assertNotNull(savedOrderFood);
        Assertions.assertEquals(savedOrderFood.getFoodName(), foodName);
        Assertions.assertEquals(savedOrderFood.getOrderDetail().getId(), orderDetailId);

        System.out.println("test passed!");
    }

    @DisplayName("2. read test")
    @Test
    void test2() {
        // when
        Optional<OrderFood> findFood = orderFoodRepository.findById(1L);

        // then
        Assertions.assertNotNull(findFood);
        findFood.ifPresent(food -> {
            Assertions.assertEquals(food.getFoodName(), foodName);
            Assertions.assertEquals(food.getOrderDetail().getId(), orderDetailId);
        });

        System.out.println("test passed!");
    }

    private OrderFood createOrderFood() {
        return OrderFood.builder()
                .foodName(foodName)
                .orderDetail(orderDetailRepository.getById(orderDetailId))
                .build();
    }

}