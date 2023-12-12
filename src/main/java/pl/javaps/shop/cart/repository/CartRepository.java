package pl.javaps.shop.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javaps.shop.cart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
