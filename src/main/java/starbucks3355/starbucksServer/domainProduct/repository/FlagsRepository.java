package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainProduct.entity.ProductFlags;

@Repository
public interface FlagsRepository extends JpaRepository<ProductFlags, Long> {
	Optional<ProductFlags> findByProductCode(Long productCode);
}
