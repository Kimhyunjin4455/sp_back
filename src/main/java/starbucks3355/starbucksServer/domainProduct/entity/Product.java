package starbucks3355.starbucksServer.domainProduct.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String productUuid;
	@Column(nullable = false, length = 100, unique = true) // 100글자 제한
	private String productName;
	@Column(length = 300)
	private String productDescription;
	@Column(length = 10000)
	private String productInfo;

	@Builder
	public Product(String productUuid, String productName, String productDescription, String productInfo) {
		this.productUuid = productUuid;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productInfo = productInfo;
	}

}
