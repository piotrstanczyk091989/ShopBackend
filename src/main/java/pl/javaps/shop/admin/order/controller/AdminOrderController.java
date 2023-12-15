package pl.javaps.shop.admin.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javaps.shop.admin.order.controller.dto.AdminOrderDto;
import pl.javaps.shop.admin.order.controller.mapper.AdminOrderMapper;
import pl.javaps.shop.admin.order.model.AdminOrder;
import pl.javaps.shop.admin.order.service.AdminOrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @GetMapping
    public Page<AdminOrderDto> getOrders(Pageable pageable){
        return AdminOrderMapper.mapToPageDtos(adminOrderService.getOrders(pageable));
    }

    @GetMapping("/{id}")
    public AdminOrder getOrder(@PathVariable Long id){
        return adminOrderService.getOrder(id);
    }
}
