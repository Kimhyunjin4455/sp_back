package starbucks3355.starbucksServer.category.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.category.entity.BottomCategory;

@Repository
public interface BottomCategoryRepository extends JpaRepository<BottomCategory, Integer> {
	boolean existsByCategoryCode(String categoryCode);

	boolean existsByCategoryName(String categoryName);

	List<BottomCategory> findByMiddleCategoryCategoryCode(String middleCategoryCode);

	Optional<BottomCategory> findByCategoryName(String categoryName);

	Optional<BottomCategory> findByCategoryCode(String categoryCode);

	// 바텀 카테고리 이름과 미들 카테고리 코드로 조회
	Optional<BottomCategory> findByCategoryNameAndMiddleCategoryCategoryCode(String categoryName,
		String middleCategoryCode);
}
