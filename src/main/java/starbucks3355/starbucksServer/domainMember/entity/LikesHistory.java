package starbucks3355.starbucksServer.domainMember.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class LikesHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String uuid;
	@Column(nullable = false)
	private String productUuid;
	@Column(nullable = false)
	private boolean isLiked = false;
	@Column(nullable = false)
	private LocalDateTime timestamp; // 찜한 시간


	@Builder
	public LikesHistory (
		Long id,
		String uuid,
		String productUuid,
		Boolean isLiked,
		LocalDateTime timestamp
	) {
		this.id = id;
		this.uuid = uuid;
		this.productUuid = productUuid;
		this.isLiked = isLiked;
		this.timestamp = timestamp;
	}
}

