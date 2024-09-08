package starbucks3355.starbucksServer.category.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "middle_category", uniqueConstraints = {@UniqueConstraint(columnNames = "top_category_id, categoryName")})
public class MiddleCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String categoryName;
	//private String categoryCode;
	//private String categoryDescription; // 카테고리 설명
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private TopCategory topCategory;
}
