package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainProduct.entity.ProductImage;

@Repository
public interface ImageRepository extends JpaRepository<ProductImage, Long> {
	Optional<ProductImage> findByProductUuid(String productUuid);
	// Q. 이미지리스트를 불러오고 그 중 대표이미지라는 컬럼값이 true 인 경우를 뽑아내는식으로 가야 하는지?
}
