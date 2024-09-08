package starbucks3355.starbucksServer.category.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.category.entity.TopCategory;

@Repository
public interface TopCategoryRepository extends JpaRepository<TopCategory, Integer> {
	//boolean existsByCategoryCode(String categoryCode);

	boolean existsByCategoryName(String categoryName);

	//Optional<TopCategory> findByCategoryCode(String categoryCode);

	Optional<TopCategory> findByCategoryName(String categoryName);

}
