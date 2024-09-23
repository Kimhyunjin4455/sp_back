package starbucks3355.starbucksServer.domainMember.entity;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String uuid;
	@Column(nullable = false)
	private String productUuid;
	@Column(nullable = false)
	private boolean isLiked = false;

	@Builder
	public Likes (
		Long id,
		String uuid,
		String productUuid,
		boolean isLiked
	) {
		this.id = id;
		this.uuid = uuid;
		this.productUuid = productUuid;
		this.isLiked = isLiked;
	}
}
