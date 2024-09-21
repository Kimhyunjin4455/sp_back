package starbucks3355.starbucksServer.domainReview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainReview.entity.ReviewAggregate;

@Repository
public interface ReviewAggregateRepository extends JpaRepository<ReviewAggregate, Long> {
}
