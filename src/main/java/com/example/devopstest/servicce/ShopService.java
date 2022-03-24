package com.example.devopstest.servicce;

import com.example.devopstest.domain.entity.Shop;
import com.example.devopstest.domain.repository.ShopRepository;
import com.example.devopstest.system.dto.request.ShopRequestDto;
import com.example.devopstest.system.dto.response.ShopResponseDto;
import com.example.devopstest.system.result.CommonResult;
import com.example.devopstest.system.result.ResultFactory;
import com.example.devopstest.system.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    // GET Method
    public ResponseEntity<SingleResult<ShopResponseDto>> read(Long id) {
        return shopRepository.findById(id).map(shop ->
                ResponseEntity.ok(ResultFactory.getSingleResult(entityToResponseDto(shop)))
                ).orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
    }

    // Post Method
    public ResponseEntity<CommonResult> create(ShopRequestDto requestDto) {
        Shop requestShop = ShopRequestDto.dtoToEntity(requestDto);

        Shop savedShop = shopRepository.save(requestShop);

        if(savedShop == null)
            throw new RuntimeException();

        return ResponseEntity.ok(ResultFactory.getSuccessResult());
    }

    private ShopResponseDto entityToResponseDto(Shop shop) {
        return ShopResponseDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .address(shop.getAddress())
                .build();
    }
}