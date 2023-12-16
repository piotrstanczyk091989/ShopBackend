package pl.javaps.shop.admin.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javaps.shop.admin.order.controller.dto.AdminInitDataDto;
import pl.javaps.shop.admin.order.controller.dto.AdminOrderDto;
import pl.javaps.shop.admin.order.controller.mapper.AdminOrderMapper;
import pl.javaps.shop.admin.order.model.AdminOrder;
import pl.javaps.shop.admin.order.model.AdminOrderStatus;
import pl.javaps.shop.admin.order.service.AdminOrderService;

import java.util.HashMap;
import java.util.Map;

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

    @PatchMapping("/{id}")
    public void patchOrder(@PathVariable Long id, @RequestBody Map<String, String> values){
        adminOrderService.patchOrder(id, values);
    }

    @GetMapping("/initData")
    public AdminInitDataDto getInitData(){
        return new AdminInitDataDto(createOrderStatusesMap());
    }

    private Map<String, String> createOrderStatusesMap() {
        HashMap<String,String> statuses = new HashMap<>();
        for (AdminOrderStatus value : AdminOrderStatus.values()) {
            statuses.put(value.name(), value.getValue());
        }
        return statuses;
    }
}
