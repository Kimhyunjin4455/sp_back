package starbucks3355.starbucksServer.shipping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import starbucks3355.starbucksServer.shipping.entity.ShippingAgree;

public interface ShippingAgreeRepository extends JpaRepository<ShippingAgree, Long> {
	Optional<ShippingAgree> findByUuid(String uuid);
}
