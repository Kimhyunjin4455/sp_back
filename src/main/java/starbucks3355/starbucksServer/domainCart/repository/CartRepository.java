package starbucks3355.starbucksServer.domainCart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainCart.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	List<Cart> findByMemberUuid(String memberUuid);

	Optional<Cart> findByMemberUuidAndProductUuid(String memberUuid, String productUuid);

	@Modifying
	void deleteByMemberUuidAndProductUuid(String memberUuid, String productUuid);

	@Modifying
	void deleteByMemberUuid(String memberUuid);
}
