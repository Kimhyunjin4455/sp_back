package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainProduct.entity.ProductImage;

@Repository
public interface ImageRepository extends JpaRepository<ProductImage, Long> {
	Optional<ProductImage> findByProductCode(Long productCode);
}
