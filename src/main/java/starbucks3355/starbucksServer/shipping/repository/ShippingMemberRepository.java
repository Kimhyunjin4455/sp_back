package starbucks3355.starbucksServer.shipping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import starbucks3355.starbucksServer.shipping.entity.ShippingMemberAddress;

public interface ShippingMemberRepository extends JpaRepository<ShippingMemberAddress, Long> {

	Optional<ShippingMemberAddress> findByUuidAndBaseAddressTrue(String uuid);

	Optional<ShippingMemberAddress> findByUuidAndDetailAddress(String uuid, String detailAddress);

	boolean existsByUuid(String uuid);
}
