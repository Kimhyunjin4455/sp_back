package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainProduct.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByProductUuid(String ProductUuid);

	Slice<Product> findPageByProductUuid(String ProductUuid, Pageable pageable);

	List<Product> findByProductNameContaining(String searchName);

}
