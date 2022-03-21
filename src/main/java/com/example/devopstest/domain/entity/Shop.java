package com.example.devopstest.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    // Shop : Menu = 1 ; N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Menu> menuList;

    // Shop : Order = 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orderList;
}
