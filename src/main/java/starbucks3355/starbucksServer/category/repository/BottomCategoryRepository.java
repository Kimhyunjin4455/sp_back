package starbucks3355.starbucksServer.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.category.entity.BottomCategory;

@Repository
public interface BottomCategoryRepository extends JpaRepository<BottomCategory, Integer> {

	List<BottomCategory> findByMiddleCategoryId(Integer middleCategoryId);

	boolean existsByMiddleCategoryIdAndCategoryName(Integer middleCategoryId, String categoryName);
}
