package starbucks3355.starbucksServer.domainReview.dto.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainReview.vo.out.MyReviewResponseVo;

@Getter
@Builder
public class MyReviewResponseDto {
	private String content;
	private String reviewUuid;
	private Integer reviewScore;
	private String productUuid; // 회원이 구매한 '상품'에 대한 리뷰
	private String memberUuid; // 미확실 필드: "시큐리티의 토큰처리에 따라" 사용여부 결정
	private LocalDateTime regDate, modDate;

	public MyReviewResponseDto(
		String content,
		String reviewUuid,
		Integer reviewScore,
		String productUuid,
		String memberUuid,
		LocalDateTime regDate,
		LocalDateTime modDate){
		this.content = content;
		this.reviewUuid = reviewUuid;
		this.reviewScore = reviewScore;
		this.productUuid = productUuid;
		this.memberUuid = memberUuid;
		this.regDate = regDate;
		this.modDate = modDate
	}

	public MyReviewResponseVo dtoToResponseVo(){
		return MyReviewResponseVo.builder()
			.content(content)
			.reviewUuid(reviewUuid)
			.reviewScore(reviewScore)
			.productUuid(productUuid)
			.memberUuid(memberUuid)
			.regDate(getRegDate())
			.modDate(getModDate())
			.build();
	}
}
