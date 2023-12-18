package pl.javaps.shop.admin.order.controller.dto;

import lombok.Builder;
import lombok.Getter;
import pl.javaps.shop.common.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class AdminOrderDto {
    private Long id;
    private LocalDateTime placeDate;
    private OrderStatus orderStatus;
    private BigDecimal grossValue;
}
