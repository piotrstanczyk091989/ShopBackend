package pl.javaps.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javaps.shop.order.model.OrderRow;

@Repository
public interface OrderRowRepository extends JpaRepository<OrderRow,Long> {
}
