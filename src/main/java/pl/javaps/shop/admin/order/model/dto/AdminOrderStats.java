package pl.javaps.shop.admin.order.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class AdminOrderStats {

    private List<Integer> label;
    private List<BigDecimal> sale;
    private List<Long> order;
    private Long ordersCount;
    private BigDecimal salesSum;

}
