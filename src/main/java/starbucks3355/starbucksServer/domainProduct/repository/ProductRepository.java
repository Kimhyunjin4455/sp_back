package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainProduct.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByProductUuid(String ProductUuid);

	Slice<Product> findPageByProductUuid(String ProductUuid, Pageable pageable);

	List<Product> findByProductNameContaining(String searchName);

	@Query("SELECT p FROM Product p WHERE p.productUuid IN :productUuids")
	List<Product> findByProductUuidIn(@Param("productUuids") List<String> productUuids);

	@Query(value = "SELECT * FROM product WHERE product_uuid = :productUuid", nativeQuery = true)
	Optional<Product> findProductByUuidNative(@Param("productUuid") String productUuid);

}
