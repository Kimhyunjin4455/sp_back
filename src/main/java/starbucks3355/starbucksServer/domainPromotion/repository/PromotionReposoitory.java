package starbucks3355.starbucksServer.domainPromotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainPromotion.entity.Promotion;

@Repository
public interface PromotionReposoitory extends JpaRepository<Promotion, Long> {
	public Promotion findByPromotionUuid(String promotionUuid);
}
