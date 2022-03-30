package com.example.devopstest.service;

import com.example.devopstest.domain.entity.Shop;
import com.example.devopstest.domain.repository.ShopRepository;
import com.example.devopstest.system.dto.request.ShopRequestDto;
import com.example.devopstest.system.dto.response.ShopResponseDto;
import com.example.devopstest.system.result.CommonResult;
import com.example.devopstest.system.result.MultipleResult;
import com.example.devopstest.system.result.ResultProvider;
import com.example.devopstest.system.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    // GET Method
    public ResponseEntity<SingleResult<ShopResponseDto>> read(Long id) {
        return shopRepository.findById(id).map(shop ->
                ResponseEntity.ok(ResultProvider.getSingleResult(entityToResponseDto(shop)))
        ).orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
    }

    // Post Method
    public ResponseEntity<CommonResult> create(ShopRequestDto requestDto) {
        Shop requestShop = ShopRequestDto.dtoToEntity(requestDto);

        Shop savedShop = shopRepository.save(requestShop);

        if (savedShop == null)
            throw new RuntimeException();

        return ResponseEntity.ok(ResultProvider.getSuccessResult());
    }

    // 모든 가게 목록을 뽑아오는 메소드
    public ResponseEntity<MultipleResult<ShopResponseDto>> searchAllShops() {
        List<Shop> allShops = shopRepository.findAll();

        if(allShops.size() == 0)
            throw new RuntimeException("가게가 존재하지 않아요!");

        List<ShopResponseDto> responseList = allShops.stream().map(shop -> entityToResponseDto(shop)
        ).collect(Collectors.toList()
        );

        return ResponseEntity.ok(ResultProvider.getMultipleResult(responseList)
        );
    }

    private ShopResponseDto entityToResponseDto(Shop shop) {
        return ShopResponseDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .address(shop.getAddress())
                .build();
    }
}