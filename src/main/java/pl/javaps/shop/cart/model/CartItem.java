package pl.javaps.shop.cart.model;

import jakarta.persistence.*;
import lombok.*;
import pl.javaps.shop.common.model.Product;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @OneToOne
    private Product product;
    private Long cartId;
}
