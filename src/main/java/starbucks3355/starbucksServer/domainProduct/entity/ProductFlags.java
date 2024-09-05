package starbucks3355.starbucksServer.domainProduct.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class ProductFlags {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private boolean isLiked; // 수정 필요, 회원의 정보가 같이 필요 -> 변화가 심함 -> noSQL
	@Column(nullable = false)
	private boolean isNew;
	@Column(nullable = false)
	private boolean isBest;
	@Column(nullable = false)
	private String productUuid;
}
