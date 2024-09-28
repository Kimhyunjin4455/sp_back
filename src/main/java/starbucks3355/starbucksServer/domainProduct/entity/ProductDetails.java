package starbucks3355.starbucksServer.domainProduct.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"productSize", "productColor"})
@Table(name = "product_details", indexes = {
	@Index(name = "idx_product_uuid", columnList = "productUuid")
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductDetails { // Options
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productCode; // id로 작성해도 문제 없지 않은지?
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "size_id")
	private ProductSize productSize; // nullable = false 설정 시 디폴트 값으로 NONE 설정에 대해 다른 계층에서 추가적인 작업을 거쳐야 함
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "color_id")
	private ProductSize productColor;
	@Column(nullable = false, length = 100)
	private String productUuid;
	private Boolean isEngraving;
	private String engravingMessage;
	@Column(nullable = false)
	private int productPrice;
	private String extraProductCode;
	@Column(nullable = false)
	private int quantityLimit;

}
