package pl.javaps.shop.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javaps.shop.product.model.Product;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts(){
        return List.of(
                new Product("Product 1", "Kategoria 1", "Opis produktu 1", new BigDecimal("10.99"), "PLN"),
                new Product("Product 2", "Kategoria 2", "Opis produktu 2", new BigDecimal("20.99"), "PLN"),
                new Product("Product 3", "Kategoria 3", "Opis produktu 3", new BigDecimal("30.99"), "PLN"),
                new Product("Product 4", "Kategoria 4", "Opis produktu 4", new BigDecimal("40.99"), "PLN")

        );
    }
}
