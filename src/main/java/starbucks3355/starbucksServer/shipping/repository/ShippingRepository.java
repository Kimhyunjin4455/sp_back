package starbucks3355.starbucksServer.shipping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import starbucks3355.starbucksServer.shipping.entity.ShippingAddress;

public interface ShippingRepository extends JpaRepository<ShippingAddress, Long> {

	@Query("select d from ShippingAddress d where d.uuid = :uuid and d.baseAddress = true")
	Optional<ShippingAddress> findBaseAddressByUuid(@Param("uuid") String uuid);

	Optional<ShippingAddress> findByDeliveryId(Long deliveryId);

	boolean existsByUuid(String uuid);

	int countByUuid(String uuid);

	boolean existsByDetailAddressAndUuid(String detailAddress, String uuid);

	List<ShippingAddress> findByUuid(String uuid);

}
