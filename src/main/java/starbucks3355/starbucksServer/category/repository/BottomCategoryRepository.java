package starbucks3355.starbucksServer.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.category.entity.BottomCategoryList;

@Repository
public interface BottomCategoryRepository extends JpaRepository<BottomCategoryList, Integer> {
}
