package starbucks3355.starbucksServer.domainProduct.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = "productOptions")
public class ProductDefaultDisCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productUuid;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DiscountType discountType;
	//일정 금액 만큼 할인값을 부여, 상품에 대해 적용할 때 상품 가격보다 더 큰값은 설정할 수 없음.
	private int discountValue;

	// public void inputDiscountValue(int discountValue, ProductDetails productDetails) {
	// 	if (discountType == DiscountType.PERCENT) {
	// 		if (discountValue < 0 || discountValue > 90) {
	// 			throw new IllegalArgumentException("PERCENT discount must be between 0 and 90.");
	// 		}
	// 	} else if (discountType == DiscountType.AMOUNT) {
	// 		double maxDiscountValue = productDetails.getProductPrice() * 0.9;
	// 		if (discountValue < 0 || discountValue > maxDiscountValue) {
	// 			throw new IllegalArgumentException("AMOUNT discount must not exceed 90% of the product price.");
	// 		}
	// 	}
	// 	this.discountValue = discountValue;
	// }
}
