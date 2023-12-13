package pl.javaps.shop.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.javaps.shop.cart.model.CartItem;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Long countByCartId(Long cartId);

    @Query("delete from CartItem ci where ci.cartId in (:ids)")
    @Modifying
    void deleteAllByCartIdIn(List<Long> ids);
}
