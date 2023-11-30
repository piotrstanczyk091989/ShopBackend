package pl.javaps.shop.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.javaps.shop.admin.controller.dto.AdminProductDto;
import pl.javaps.shop.admin.model.AdminProduct;
import pl.javaps.shop.admin.service.AdminProductService;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class AdminProductController {

    public static final Long EMPTY_ID = null;
    private final AdminProductService adminProductService;

    @GetMapping("/admin/products")
    public Page<AdminProduct> getProducts(Pageable pageable){
        return adminProductService.getProducts(pageable);
    }

    @GetMapping("/admin/products/{id}")
    public AdminProduct getProduct(@PathVariable Long id){
        return adminProductService.getProduct(id);
    }

    @PostMapping("/admin/products")
    public AdminProduct creteProduct(@RequestBody AdminProductDto adminProductDto){
        return adminProductService.creteProduct(mapAdminProduct(adminProductDto, EMPTY_ID)
        );
    }

    @PutMapping("/admin/products/{id}")
    public AdminProduct updateProduct(@RequestBody AdminProductDto adminProductDto, @PathVariable Long id){
        return adminProductService.updateProduct(mapAdminProduct(adminProductDto, id)
        );
    }

    private static AdminProduct mapAdminProduct(AdminProductDto adminProductDto, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProductDto.getName())
                .description(adminProductDto.getDescription())
                .category(adminProductDto.getCategory())
                .price(adminProductDto.getPrice())
                .currency(adminProductDto.getCurrency().toUpperCase(Locale.ROOT))
                .build();
    }

}
