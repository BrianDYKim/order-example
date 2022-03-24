package com.example.devopstest.domain.repository;

import com.example.devopstest.domain.entity.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopRepositoryTest {

    @Autowired
    private ShopRepository shopRepository;

    private String name = "테스트 가게";
    private String address = "강남역 10번 출구";

    @DisplayName("1. create test")
    @Test
    void test1() {
        Shop newShop = createShop();

        Shop savedShop = shopRepository.save(newShop);

        Assertions.assertEquals(savedShop.getName(), name);
        Assertions.assertEquals(savedShop.getAddress(), address);
    }

    @DisplayName("2. read test")
    @Test
    @Transactional
    void test2() {
        // when
        Optional<Shop> findShop = shopRepository.findById(1L);

        // then
        Assertions.assertNotNull(findShop);
        findShop.ifPresent(shop -> {
            Assertions.assertEquals(shop.getName(), name);
            Assertions.assertEquals(shop.getAddress(), address);

            shop.getMenuList().forEach(menu -> {
                System.out.println("메뉴 이름 : " + menu.getName());
            });
        });

        System.out.println("test passed!");
    }

    private Shop createShop() {
        return Shop.builder()
                .name(name)
                .address(address)
                .build();
    }

}