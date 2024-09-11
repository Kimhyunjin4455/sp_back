package starbucks3355.starbucksServer.domainOrders.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.domainOrders.entity.Amount;

@Getter
@NoArgsConstructor
@ToString
public class KakaoResponseApproveDto {
	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partnerOrderId;
	private String partnerUserId;
	private String paymentMethodType;
	private Amount amount;
}
