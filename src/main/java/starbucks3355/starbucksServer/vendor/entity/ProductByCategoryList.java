package starbucks3355.starbucksServer.vendor.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "product_by_category_list", indexes = {
	@Index(name = "idx_product_uuid", columnList = "productUuid")
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductByCategoryList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String topCategoryName;

	private String middleCategoryName;

	private String productUuid;
}
