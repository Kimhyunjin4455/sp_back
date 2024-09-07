package starbucks3355.starbucksServer.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "top_category", uniqueConstraints = {@UniqueConstraint(columnNames = "categoryCode, categoryName")})
public class TopCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String categoryName; // 카테고리 이름
	//private String categoryCode; // 이름에 대한 코드 (사과에 하나의 코드 부여)
	//private String categoryDescription; // 카테고리 설명
	// @Column(nullable = false, length = 255)
	// private String categoryImg;
}
