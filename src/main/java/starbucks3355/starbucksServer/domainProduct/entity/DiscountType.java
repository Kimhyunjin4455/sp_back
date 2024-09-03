package starbucks3355.starbucksServer.domainProduct.entity;

import lombok.Getter;

@Getter
public enum DiscountType {
	PERCENT("퍼센트 할인"), AMOUNT("금액 할인");

	private final String discountType;

	DiscountType(String discountType) {
		this.discountType = discountType;
	}

}
