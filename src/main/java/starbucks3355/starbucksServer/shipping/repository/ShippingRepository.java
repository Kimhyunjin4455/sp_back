package starbucks3355.starbucksServer.shipping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import starbucks3355.starbucksServer.shipping.entity.ShippingAddress;

public interface ShippingRepository extends JpaRepository<ShippingAddress, Long> {

	boolean existsByAddress(String address);

	// @Query("select d from Delivery d where d.uuid = :uuid and d.baseAddress = true")
	// Optional<Delivery> findBaseDelivery(@Param("uuid") String uuid);

	@Query("select d from ShippingAddress d JOIN ShippingMemberAddress m on d.uuid =m.uuid where d.uuid= :uuid and d.baseAddress =true and m.baseAddress =true ")
	Optional<ShippingAddress> findBaseDeliveryByUuid(@Param("uuid") String uuid);

	ShippingAddress findByDeliveryId(Long deliveryId);

	Optional<ShippingAddress> findByUuid(String uuid);

	boolean existsByUuid(String uuid);
}
