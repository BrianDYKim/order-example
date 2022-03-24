package com.example.devopstest.domain.repository;

import com.example.devopstest.domain.entity.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ShopRepository shopRepository;

    private String name = "존맛 부대찌개";
    private Long shopId = 1L;

    @DisplayName("1. create test")
    @Test
    void test1() {
        // given
        Menu newMenu = createMenu();

        // when
        Menu savedMenu = menuRepository.save(newMenu);

        // then
        Assertions.assertNotNull(savedMenu);
        Assertions.assertEquals(savedMenu.getName(), name);
        Assertions.assertEquals(savedMenu.getShop().getId(), shopId);

        System.out.println("test passed!");
    }

    @DisplayName("2. read test")
    @Test
    void test2() {
        // when
        Optional<Menu> findMenu = menuRepository.findById(1L);

        // then
        Assertions.assertNotNull(findMenu);
        findMenu.ifPresent(menu -> {
            Assertions.assertEquals(menu.getName(), name);
            Assertions.assertEquals(menu.getShop().getId(), shopId);
        });

        System.out.println("test passed!");
    }

    private Menu createMenu() {
        return Menu.builder()
                .name(name)
                .shop(shopRepository.getById(shopId))
                .build();
    }

}