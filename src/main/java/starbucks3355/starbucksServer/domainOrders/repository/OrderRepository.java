package starbucks3355.starbucksServer.domainOrders.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainOrders.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

	Optional<Orders> findByUuid(UUID uuid);
}
