package pl.javaps.shop.order.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javaps.shop.order.model.Shipment;
import pl.javaps.shop.order.repository.ShipmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public List<Shipment> getShipment(){
        return shipmentRepository.findAll();
    }

}
