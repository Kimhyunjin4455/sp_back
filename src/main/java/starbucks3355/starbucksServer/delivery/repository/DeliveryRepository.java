package starbucks3355.starbucksServer.delivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import starbucks3355.starbucksServer.delivery.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

	boolean existsByAddress(String address);

	boolean existsByIsBase(boolean isBase);

	//List<Delivery> findByIsBase(boolean isBase);

	Optional<Delivery> findByDeliveryIdAndIsBase(Long deliveryId, boolean isBase);

	Delivery findByDeliveryId(Long deliveryId);
}
