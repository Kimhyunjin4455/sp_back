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

	Slice<Review> findPageByProductUuid(String productUuid, Pageable pageable);

	// 상품의 리뷰들 중 평점이 높고 조회수가 높은 리뷰들을 5개까지 반환
	List<Review> findTop5ByProductUuidOrderByReviewScoreDescReviewViewCountDesc(String productUuid);

}
