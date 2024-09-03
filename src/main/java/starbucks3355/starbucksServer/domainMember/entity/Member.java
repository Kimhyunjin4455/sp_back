package starbucks3355.starbucksServer.domainMember.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String userId;
    @Column(nullable = false, length = 30)
    private String password;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(length = 30)
    private String nickname;
    @LastModifiedDate
    private LocalDateTime withdrawalTime;
    private UUID memberUuid;
}
