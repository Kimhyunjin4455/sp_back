package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainProduct.entity.ProductTag;

@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {
	List<ProductTag> findByTagNameContaining(String searchTag);
}
