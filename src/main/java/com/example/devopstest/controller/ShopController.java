package com.example.devopstest.controller;

import com.example.devopstest.service.ShopService;
import com.example.devopstest.system.dto.request.ShopRequestDto;
import com.example.devopstest.system.dto.response.ShopResponseDto;
import com.example.devopstest.system.result.CommonResult;
import com.example.devopstest.system.result.MultipleResult;
import com.example.devopstest.system.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/shop")
@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/{id}")
    public ResponseEntity<SingleResult<ShopResponseDto>> read(@PathVariable Long id) {
        return shopService.read(id);
    }

    // Post Method
    @PostMapping("")
    public ResponseEntity<CommonResult> create(@RequestBody ShopRequestDto requestDto) {
        return shopService.create(requestDto);
    }

    @GetMapping("/all-list")
    public ResponseEntity<MultipleResult<ShopResponseDto>> searchAllShops() {
        return shopService.searchAllShops();
    }

    // For Health Check
    @GetMapping("/check")
    public String healthCheck() {
        return "Health check!!";
    }
}
