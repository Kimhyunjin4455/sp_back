package starbucks3355.starbucksServer.domainProduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainProduct.entity.TopCategoryList;

@Repository
public interface TopCategoryRepository extends JpaRepository<TopCategoryList, Integer> {
}
