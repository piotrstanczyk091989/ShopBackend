package pl.javaps.shop.admin.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javaps.shop.admin.order.model.AdminOrder;
import pl.javaps.shop.admin.order.model.AdminOrderStatus;
import pl.javaps.shop.admin.order.repository.AdminOrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminExportService {

    private final AdminOrderRepository orderRepository;

    public List<AdminOrder> exportOrders(LocalDateTime from, LocalDateTime to, AdminOrderStatus orderStatus) {
        return orderRepository.findAllByPlaceDateIsBetweenAndOrderStatus(from,to,orderStatus);
    }
}
