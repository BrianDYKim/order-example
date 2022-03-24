package com.example.devopstest.system.dto.request;

import com.example.devopstest.domain.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopRequestDto {

    private String name;

    private String address;

    public static Shop dtoToEntity(ShopRequestDto requestDto) {
        return Shop.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .build();
    }
}
