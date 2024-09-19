package starbucks3355.starbucksServer.domainProduct.entity;

import lombok.Getter;

@Getter
public enum DiscountType {
	PERCENT("퍼센트 할인");
	// 추가적인 할인정책으로의 개선 가능성이 존재하기에 enum으로 처리

	private final String discountType;

	DiscountType(String discountType) {
		this.discountType = discountType;
	}

}
