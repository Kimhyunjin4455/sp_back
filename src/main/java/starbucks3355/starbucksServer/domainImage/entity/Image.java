package starbucks3355.starbucksServer.domainImage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "image", indexes = {
	@Index(name = "idx_other_uuid", columnList = "otherUuid")
})
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 1000)
	private String s3url;
	@Column(nullable = false, length = 300)
	private String imageName; // 삭제될 수도 있는 컬럼
	@Column(length = 250)
	private String thumbnailPath;
	@Column(nullable = false)
	private String imageUuid;
	@Column(nullable = false)
	private boolean isMainImage; // 대표이미지 여부, 이 값 통해 상품의 대표이미지, 리뷰의 썸네일 판단, 응답 dto에 있어야될지?
	@Column(nullable = false)
	private String otherUuid; // 상품, 쿠폰, 리뷰 등은 각각 자신만의 이미지를 보유 -> 각종 uuid값을 통해 연결할 필드
	//private String anotherUuid; // 다른 uuid값을 가진 이미지와 연결할 필드

	public void modifyIsMainImage(boolean isMainImage) {
		this.isMainImage = isMainImage;
	}

}
