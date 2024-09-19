package starbucks3355.starbucksServer.domainReview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.common.entity.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 200)
	private String content;
	@Column(nullable = false)
	private String reviewUuid;
	private Integer reviewScore;
	@Column(nullable = false)
	private String productUuid; // 상품의 정보가 너무 많기에, 매핑 테이블 요구됨
	@Column(nullable = false)
	private String memberUuid; // 정원이와 타입 통일 필요, uuid보단 닉네임
	@Column
	private Integer reviewViewCount; // 조회수 -> 동시성문제, 집계테이블 필요, 베스트는 db의 더미깂 통해 해결

	public void modifyContent(String content) {
		this.content = content;
	}

	public void modifyReviewScore(Integer reviewScore) {
		this.reviewScore = reviewScore;
	}

	public void modifyReviewViewCount(Integer reviewViewCount) {
		this.reviewViewCount = reviewViewCount;
	}

}
