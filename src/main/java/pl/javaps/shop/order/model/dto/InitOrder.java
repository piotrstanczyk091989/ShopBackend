package pl.javaps.shop.order.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.javaps.shop.order.model.Payment;
import pl.javaps.shop.order.model.Shipment;

import java.util.List;

@Getter
@Builder
public class InitOrder {
    List<Shipment> shipment;
    List<Payment> payment;
}
