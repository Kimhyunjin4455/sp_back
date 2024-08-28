package starbucks3355.starbucksServer.domainOrders.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, length = 30)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Orders orders;

	@Column(nullable = false, length = 30)
	private Integer productCnt;
	@Column(nullable = false, length = 30)
	private String productCode;
	@Column(nullable = false, length = 30)
	private Integer detailPrice;
	@Column(nullable = false, length = 30)
	private String detailName;
	@Column(nullable = false, length = 30)
	private String detailColor;
	@Column(nullable = false, length = 30)
	private String detailSize;
	@Column(nullable = false, length = 30)
	private String detailEngraving;

}
