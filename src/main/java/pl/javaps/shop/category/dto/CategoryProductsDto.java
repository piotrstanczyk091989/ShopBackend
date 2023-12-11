package pl.javaps.shop.category.dto;

import org.springframework.data.domain.Page;
import pl.javaps.shop.common.dto.ProductListDto;
import pl.javaps.shop.common.model.Category;

public record CategoryProductsDto(Category category, Page<ProductListDto> products){
}
