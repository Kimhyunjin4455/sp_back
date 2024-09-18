package starbucks3355.starbucksServer.shipping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import starbucks3355.starbucksServer.shipping.entity.ShippingAddress;

public interface ShippingRepository extends JpaRepository<ShippingAddress, Long> {

	boolean existsByAddress(String address);

	// @Query("select d from Delivery d where d.uuid = :uuid and d.baseAddress = true")
	// Optional<Delivery> findBaseDelivery(@Param("uuid") String uuid);

	//@Query("select d from ShippingAddress d JOIN ShippingMemberAddress m on d.uuid =m.uuid where d.uuid= :uuid and d.baseAddress =true and m.baseAddress =true ")
	//Optional<ShippingAddress> findBaseDeliveryByUuidWithJoin(@Param("uuid") String uuid);

	@Query("select d from ShippingAddress d where d.uuid = :uuid and d.baseAddress = true")
	Optional<ShippingAddress> findBaseDeliveryByUuid(@Param("uuid") String uuid);

	ShippingAddress findByDeliveryId(Long deliveryId);

	//Optional<ShippingAddress> findByUuid(String uuid);

	boolean existsByUuid(String uuid);

	int countByUuid(String uuid);

	boolean existsByDetailAddressAndUuid(String detailAddress, String uuid);

	List<ShippingAddress> findByUuid(String uuid);

	// //memberAddressId로 ShippingAddress 조회
	// @Query("select sa from ShippingAddress sa JOIN ShippingMemberAddress sm On sa.uuid = sm.uuid where sm.memberAddressId = :memberAddressId")
	// List<ShippingAddress> findAllByMemberAddressId(@Param("memberAddressId") Long memberAddressId);

}
