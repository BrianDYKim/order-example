package com.example.devopstest.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    private Integer deliverEstimatedTime;

    private Boolean deliverFinished;

    private String address;

    // Order : Shop = N : 1
    @ManyToOne
    @JsonBackReference
    private Shop shop;

    // Order : OrderFood = 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderDetail", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderFood> orderFoodList;
}
