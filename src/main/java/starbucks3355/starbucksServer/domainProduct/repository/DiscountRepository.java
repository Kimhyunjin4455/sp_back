package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainProduct.entity.ProductDefaultDisCount;

@Repository
public interface DiscountRepository extends JpaRepository<ProductDefaultDisCount, Long> {
	public Optional<ProductDefaultDisCount> findByProductUuid(String productUuid);

}
