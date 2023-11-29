package pl.javaps.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javaps.shop.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
