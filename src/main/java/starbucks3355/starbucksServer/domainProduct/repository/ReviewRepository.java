package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainProduct.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	Optional<Review> findByMemberUuidAndProductCode(String memberUuid, Long productCode);

	// Optional<Review> findByReviewCount(Long productCode);

}
