package starbucks3355.starbucksServer.domainReview.dto.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainReview.vo.out.ReviewResponseVo;

@Getter
@Builder
public class ReviewResponseDto { // 상품 한개에 대한 리뷰 응답 dto
	private String content;
	private Integer reivewScore;
	private LocalDateTime regDate, modDate;

	public ReviewResponseDto(
		String content,
		Integer reivewScore,
		LocalDateTime regDate,
		LocalDateTime modDate) {
		this.content = content;
		this.reivewScore = reivewScore;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public ReviewResponseVo dtoToResponseVo() {
		return ReviewResponseVo.builder()
			.content(content)
			.reviewScore(reivewScore)
			.regDate(getRegDate())
			.modDate(getModDate())
			.build();
	}
}
