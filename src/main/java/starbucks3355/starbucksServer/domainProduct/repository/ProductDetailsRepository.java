package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainProduct.entity.ProductDetails;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
	Optional<ProductDetails> findByProductUuid(String productUuid);

	@Query("SELECT pd FROM ProductDetails pd WHERE pd.productUuid IN :productUuids")
	List<ProductDetails> findByProductUuidIn(@Param("productUuids") List<String> productUuids);

}
