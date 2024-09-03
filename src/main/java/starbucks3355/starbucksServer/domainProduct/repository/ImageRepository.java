package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import starbucks3355.starbucksServer.domainProduct.entity.ProductImage;

public interface ImageRepository extends JpaRepository<ProductImage, Long> {
	Optional<ProductImage> findByS3Url(String imageId, String productId);
}
