package pl.javaps.shop.order.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javaps.shop.common.mail.EmailClientService;
import pl.javaps.shop.common.model.Cart;
import pl.javaps.shop.common.repository.CartItemRepository;
import pl.javaps.shop.common.repository.CartRepository;
import pl.javaps.shop.order.model.Order;
import pl.javaps.shop.order.model.Payment;
import pl.javaps.shop.order.model.Shipment;
import pl.javaps.shop.order.model.dto.OrderDto;
import pl.javaps.shop.order.model.dto.OrderListDto;
import pl.javaps.shop.order.model.dto.OrderSummary;
import pl.javaps.shop.order.repository.OrderRepository;
import pl.javaps.shop.order.repository.OrderRowRepository;
import pl.javaps.shop.order.repository.PaymentRepository;
import pl.javaps.shop.order.repository.ShipmentRepository;

import java.util.List;

import static pl.javaps.shop.order.service.mapper.OrderDtoMapper.mapToOrderListDto;
import static pl.javaps.shop.order.service.mapper.OrderEmailMessageMapper.createEmailMessage;
import static pl.javaps.shop.order.service.mapper.OrderMapper.createNewOrder;
import static pl.javaps.shop.order.service.mapper.OrderMapper.createOrderSummary;
import static pl.javaps.shop.order.service.mapper.OrderMapper.mapToOrderRow;
import static pl.javaps.shop.order.service.mapper.OrderMapper.mapToOrderRowWithQuantity;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailClientService emailClientService;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto, Long userId) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order newOrder = orderRepository.save(createNewOrder(orderDto, cart, shipment, payment, userId));
        saveOrderRows(cart, newOrder.getId(), shipment);
        clearOrderCart(orderDto);
        log.info("Zamówienie złożone");
        sendConfirmEmail(newOrder);
        return createOrderSummary(newOrder, payment);
    }

    private void sendConfirmEmail(Order newOrder) {
        emailClientService.getInstance()
                .send(newOrder.getEmail(), "Twoje zamówienie zostało przyjęte", createEmailMessage(newOrder));
    }

    private void clearOrderCart(OrderDto orderDto) {
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteByCartId(orderDto.getCartId());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToOrderRow(orderId, shipment));
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems().stream()
                .map(cartItem -> mapToOrderRowWithQuantity(orderId, cartItem)
                )
                .peek(orderRowRepository::save)
                .toList();
    }

    public List<OrderListDto> getOrdersFromCustomer(Long userId) {
        return mapToOrderListDto(orderRepository.findByUserId(userId));
    }

}
