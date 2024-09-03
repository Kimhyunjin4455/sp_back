package starbucks3355.starbucksServer.domainProduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import starbucks3355.starbucksServer.domainProduct.entity.ProductImage;

public interface ProductImgRepository extends JpaRepository<ProductImage, Long> {
}
