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
	private String productUuid;
	@Column(nullable = false)
	private String memberUuid; // 정원이와 타입 통일 필요

	public void modifyContent(String content) {
		this.content = content;
	}

	public void modifyReviewScore(Integer reviewScore) {
		this.reviewScore = reviewScore;
	}

}
