package starbucks3355.starbucksServer.delivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import starbucks3355.starbucksServer.delivery.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

	boolean existsByAddress(String address);

	@Query("select d from Delivery d where d.uuid = :uuid and d.baseAddress = true")
	Optional<Delivery> findBaseDelivery(@Param("uuid") String uuid);

	Delivery findByDeliveryId(Long deliveryId);

	Optional<Delivery> findByUuid(String uuid);

	boolean existsByUuid(String uuid);
}
