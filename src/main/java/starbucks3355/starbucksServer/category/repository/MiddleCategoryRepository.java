package starbucks3355.starbucksServer.category.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.category.entity.MiddleCategory;

@Repository
public interface MiddleCategoryRepository extends JpaRepository<MiddleCategory, Integer> {
	//boolean existsByCategoryCode(String categoryCode);

	boolean existsByCategoryName(String categoryName);

	//Optional<MiddleCategory> findByCategoryCode(String categoryCode);

	Optional<MiddleCategory> findByCategoryName(String categoryName);

	// 미들 카테고리 이름과 탑 카테고리 코드로 조회
	//Optional<MiddleCategory> findByCategoryNameAndTopCategoryCategoryCode(String categoryName, String topCategoryCode);

	//List<MiddleCategory> findByTopCategoryCategoryCode(String topCategoryCode);

	// 중 카테고리를 topCategoryId로 조회하는 메서드
	List<MiddleCategory> findByTopCategoryId(Integer topCategoryId);
}
