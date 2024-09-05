package starbucks3355.starbucksServer.domainReview.dto.out;

import lombok.Getter;

@Getter
public class MyReviewResponseDto {
	private String content;
	private String reviewUuid;
	private Integer reivewScore;
	private String productUuid; // 회원이 구매한 '상품'에 대한 리뷰
	private String memberUuid; // 미확실 필드: 시큐리티의 토큰처리에 따라 사용여부 결정
}
