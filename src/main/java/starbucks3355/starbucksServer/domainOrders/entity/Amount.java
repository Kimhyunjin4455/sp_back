package starbucks3355.starbucksServer.domainOrders.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Amount {
	private int total; // 총 결제 금액
	private int taxFree; // 비과세 금액
	private int tax; // 부가세 금액
	private int point; // 사용한 포인트
	private int discount; // 할인금액
	private int greenDeposit; // 컵 보증금

	@Builder
	public Amount(int total, int taxFree, int tax, int point, int discount, int greenDeposit) {
		this.total = total;
		this.taxFree = taxFree;
		this.tax = tax;
		this.point = point;
		this.discount = discount;
		this.greenDeposit = greenDeposit;
	}
}
