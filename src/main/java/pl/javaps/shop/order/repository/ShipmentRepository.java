package pl.javaps.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javaps.shop.order.model.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
}
