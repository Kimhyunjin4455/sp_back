package starbucks3355.starbucksServer.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.vendor.entity.ProductByPromotionList;

@Repository
public interface ProductListByPromotionRepository extends JpaRepository<ProductByPromotionList, Long> {

}

