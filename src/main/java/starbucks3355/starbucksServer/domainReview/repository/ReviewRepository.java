package starbucks3355.starbucksServer.domainReview.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainReview.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByReviewUuid(String reviewUuid);

	List<Review> findByProductUuid(String productUuid);

	List<Review> findByMemberUuid(String memberUuid);

	// 페이지네이션을 위한 메소드 추가
	Slice<Review> findPageByProductUuid(String productUuid, Pageable pageable);

}
