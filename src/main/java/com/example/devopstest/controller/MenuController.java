package com.example.devopstest.controller;

import com.example.devopstest.service.MenuService;
import com.example.devopstest.system.dto.request.MenuRequestDto;
import com.example.devopstest.system.dto.response.MenuResponseDto;
import com.example.devopstest.system.result.CommonResult;
import com.example.devopstest.system.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/{id}")
    public ResponseEntity<SingleResult<MenuResponseDto>> read(@PathVariable Long id) {
        return menuService.read(id);
    }

    @PostMapping("")
    public ResponseEntity<CommonResult> create(@RequestBody MenuRequestDto requestDto) {
        return menuService.create(requestDto);
    }
}