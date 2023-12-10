package pl.javaps.shop.category.model;

import org.springframework.data.domain.Page;
import pl.javaps.shop.product.model.Product;

public record CategoryProductsDto(Category category, Page<Product> products){
}
