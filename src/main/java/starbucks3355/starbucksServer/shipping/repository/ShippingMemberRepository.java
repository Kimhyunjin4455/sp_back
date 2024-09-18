package starbucks3355.starbucksServer.shipping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import starbucks3355.starbucksServer.shipping.entity.ShippingMemberAddress;

public interface ShippingMemberRepository extends JpaRepository<ShippingMemberAddress, Long> {

	Optional<ShippingMemberAddress> findByUuidAndBaseAddressTrue(String uuid);

	Optional<ShippingMemberAddress> findByUuidAndDetailAddress(String uuid, String detailAddress);

	boolean existsByUuid(String uuid);

	@Modifying
	@Query("update ShippingMemberAddress s set s.baseAddress = false where s.uuid = :uuid")
	void updateAllBaseAddressToFalse(@Param("uuid") String uuid);

	//Optional<ShippingMemberAddress> findByUuidAndMemberAddressId(String uuid, Long deliveryId);

	//Optional<ShippingMemberAddress> findByMemberAddressIdAndBaseAddressTrue(String uuid);

}
