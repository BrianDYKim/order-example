package com.example.devopstest.system.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponseDto {

    private Long id;

    private String name;

    @JsonProperty("shop_name")
    private String shopName;
}
