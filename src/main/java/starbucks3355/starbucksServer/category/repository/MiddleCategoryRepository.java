package starbucks3355.starbucksServer.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.category.entity.MiddleCategory;

@Repository
public interface MiddleCategoryRepository extends JpaRepository<MiddleCategory, Integer> {

	boolean existsByTopCategoryIdAndCategoryName(Integer topCategoryId, String categoryName);

	// 중 카테고리를 topCategoryId로 조회하는 메서드
	List<MiddleCategory> findByTopCategoryId(Integer topCategoryId);

	// 탑 카테고리 id를 통해 미들 카테고리의 이름이 카테고리 조회

	@Query(value = "SELECT * FROM middle_category WHERE top_category_id = :topCategoryId AND category_name = :middleCategoryName", nativeQuery = true)
	List<MiddleCategory> findByTopCategoryIdAndCategoryNameUsingQuery(
		@Param("topCategoryId") Integer topCategoryId, @Param("middleCategoryName") String middleCategoryName);

}
