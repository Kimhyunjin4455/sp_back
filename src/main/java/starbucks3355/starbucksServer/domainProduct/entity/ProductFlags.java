package starbucks3355.starbucksServer.domainProduct.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class ProductFlags {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private boolean isLiked;
	@Column(nullable = false)
	private boolean isNew;
	@Column(nullable = false)
	private boolean isBest;
	@Column(nullable = false)
	private Long productCode;
}
