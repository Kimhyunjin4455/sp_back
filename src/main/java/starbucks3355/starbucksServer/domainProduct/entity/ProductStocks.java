package starbucks3355.starbucksServer.domainProduct.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "productOptions")
public class ProductStocks { // 일괄 처리 필요 Batch, Details 들어간다고 크게 문제 없을 것 같음
	@Id
	@JoinColumn(name = "option_id")
	private Long id;
	@Column(nullable = false)
	private int count;
	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id", nullable = false)
	private ProductDetails productOptions;

}
