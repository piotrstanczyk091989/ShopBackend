package pl.javaps.shop.order.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javaps.shop.common.model.Cart;
import pl.javaps.shop.common.model.CartItem;
import pl.javaps.shop.common.repository.CartItemRepository;
import pl.javaps.shop.common.repository.CartRepository;
import pl.javaps.shop.order.model.Order;
import pl.javaps.shop.order.model.OrderRow;
import pl.javaps.shop.order.model.OrderStatus;
import pl.javaps.shop.order.model.dto.OrderDto;
import pl.javaps.shop.order.model.dto.OrderSummary;
import pl.javaps.shop.order.repository.OrderRepository;
import pl.javaps.shop.order.repository.OrderRowRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        //tworzenie zamówienia z wierszami
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Order order = Order.builder()
                .firstname(orderDto.getFirstname())
                .lastname(orderDto.getLastname())
                .street(orderDto.getStreet())
                .zipcode(orderDto.getZipcode())
                .city(orderDto.getCity())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .placeDate(LocalDateTime.now())
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getItems()))
                .build();
        //zapisać zamówienie
        Order newOrder = orderRepository.save(order);
        //pobrać koszyk
        saveOrderRows(cart,newOrder.getId());
        //usunąć koszyk
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteByCartId(orderDto.getCartId());
        //zwrócić podsumowanie
        return OrderSummary.builder()
                .id(newOrder.getId())
                .placeDate(newOrder.getPlaceDate())
                .status(newOrder.getOrderStatus())
                .grossValue(newOrder.getGrossValue())
                .build();
    }

    private BigDecimal calculateGrossValue(List<CartItem> items) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private void saveOrderRows(Cart cart, Long id) {
        cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .quantity(cartItem.getQuantity())
                        .productId(cartItem.getProduct().getId())
                        .price(cartItem.getProduct().getPrice())
                        .orderId(id)
                        .build()
                )
                .peek(orderRowRepository::save)
                .toList();
    }
}
