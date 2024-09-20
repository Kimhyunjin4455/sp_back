package starbucks3355.starbucksServer.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter

@NoArgsConstructor
@ToString
public class CategoryList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer majorCategoryName;
	private Integer middleCategoryName;
	private Integer bottomCategoryName;
	private String productUuid;

	@Builder
	public CategoryList(Long id, Integer majorCategoryName, Integer middleCategoryName, Integer bottomCategoryName,
		String productUuid) {
		this.id = id;
		this.majorCategoryName = majorCategoryName;
		this.middleCategoryName = middleCategoryName;
		this.bottomCategoryName = bottomCategoryName;
		this.productUuid = productUuid;
	}
}
