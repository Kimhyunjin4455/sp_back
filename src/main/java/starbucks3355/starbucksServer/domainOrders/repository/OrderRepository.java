package starbucks3355.starbucksServer.domainOrders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import starbucks3355.starbucksServer.domainOrders.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
