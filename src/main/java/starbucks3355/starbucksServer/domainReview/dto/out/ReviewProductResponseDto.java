package starbucks3355.starbucksServer.domainReview.dto.out;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainReview.vo.out.ReviewProductResponseVo;

@Getter
@Builder
@AllArgsConstructor
public class ReviewProductResponseDto {
	private String content;
	private String reviewUuid;
	private Integer reviewScore;
	private String productUuid;
	private String memberUuid;
	private String userId;
	private LocalDateTime regDate, modDate;

	public ReviewProductResponseVo dtoToResponseVo() {
		return ReviewProductResponseVo.builder()
			.content(content)
			.reviewScore(reviewScore)
			.reviewUuid(reviewUuid)
			.productUuid(productUuid)
			.userId(userId)
			.regDate(getRegDate())
			.modDate(getModDate())
			.build();
	}

}
