package com.example.devopstest.servicce;

import com.example.devopstest.domain.entity.Menu;
import com.example.devopstest.domain.entity.Shop;
import com.example.devopstest.domain.repository.MenuRepository;
import com.example.devopstest.domain.repository.ShopRepository;
import com.example.devopstest.system.dto.request.MenuRequestDto;
import com.example.devopstest.system.dto.response.MenuResponseDto;
import com.example.devopstest.system.result.CommonResult;
import com.example.devopstest.system.result.ResultFactory;
import com.example.devopstest.system.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private final ShopRepository shopRepository;

    public ResponseEntity<SingleResult<MenuResponseDto>> read(Long id) {
        return menuRepository.findById(id).map(menu -> ResponseEntity.ok(
                ResultFactory.getSingleResult(entityToResponseDto(menu)))
        ).orElseThrow(() -> new EntityNotFoundException("Entity not found!")
        );
    }

    public ResponseEntity<CommonResult> create(MenuRequestDto requestDto) {
        // request가 들어오지 않은 경우
        if(requestDto == null)
            throw new RuntimeException();

        Menu requestMenu = requestDtoToEntity(requestDto);

        Menu savedMenu = menuRepository.save(requestMenu);

        if(savedMenu == null)
            throw new RuntimeException();

        return ResponseEntity.ok(ResultFactory.getSuccessResult());
    }

    // entity -> responseDto
    private MenuResponseDto entityToResponseDto(Menu menu) {
        return MenuResponseDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .shopName(menu.getShop().getName())
                .build();
    }

    // requestDto -> entity
    // 무조건 service logic 내부에서만 사용할 메소드이기 때문에 여기서 예외처리를 해주면 되는 것임.
    private Menu requestDtoToEntity(MenuRequestDto requestDto) {
        Optional<Shop> foundShop = shopRepository.findById(requestDto.getShopId());

        return foundShop.map(shop -> Menu.builder()
                .name(requestDto.getName())
                .shop(shop)
                .build()
        ).orElseThrow(() -> new EntityNotFoundException("Entity not found!")
        );
    }
}
