package starbucks3355.starbucksServer.domainOrders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainOrders.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

	List<Orders> findOrderResponseVoListByUserId(String userId);

	Orders findByUserIdAndOrderId(String userId, String orderId);
}
