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
@Table(name = "bottom_category", uniqueConstraints = {@UniqueConstraint(columnNames = "categoryName, categoryCode")})
public class BottomCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String categoryName;
	private String categoryCode;
	private String categoryDescription; // 카테고리 설명
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private MiddleCategory middleCategory;

}
