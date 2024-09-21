package starbucks3355.starbucksServer.domainReview.entity;

import jakarta.persistence.Column;
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
public class ReviewAggregate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String reviewUuid; // 리뷰 UUID

	@Column(nullable = false)
	private Integer viewCount; // 조회수

	@Column(nullable = false)
	private Integer reviewScore; // 리뷰 평점

	@Builder
	public ReviewAggregate(Long id, String reviewUuid, Integer viewCount, Integer reviewScore) {
		this.id = id;
		this.reviewUuid = reviewUuid;
		this.viewCount = viewCount;
		this.reviewScore = reviewScore;
	}

	@Builder
	public ReviewAggregate(String reviewUuid, Integer viewCount, Integer reviewScore) {
		this.reviewUuid = reviewUuid;
		this.viewCount = viewCount;
		this.reviewScore = reviewScore;
	}

	// 조회수 증가 메서드
	public ReviewAggregate incrementViewCount() {
		return ReviewAggregate.builder()
			.id(this.id)
			.reviewUuid(this.reviewUuid)
			.viewCount(this.viewCount + 1) // 조회수 증가
			.reviewScore(this.reviewScore)
			.build();
	}
}
