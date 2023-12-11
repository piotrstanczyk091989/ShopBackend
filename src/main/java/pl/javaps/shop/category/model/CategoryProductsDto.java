package pl.javaps.shop.category.model;

import org.springframework.data.domain.Page;
import pl.javaps.shop.product.controller.dto.ProductListDto;

public record CategoryProductsDto(Category category, Page<ProductListDto> products){
}
