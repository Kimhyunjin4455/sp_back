package starbucks3355.starbucksServer.domainReview.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainReview.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByReviewUuid(String reviewUuid);

	List<Review> findByProductUuid(String productUuid);

	List<Review> findByMemberUuid(String memberUuid);
}
