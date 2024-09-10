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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String userId;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(length = 30)
    private String nickname;
    @LastModifiedDate
    private LocalDateTime withdrawalTime;
    private String uuid;

    @Builder
    public Member(
        Long id,
        String userId,
        String name,
        String password,
        String email,
        String nickname,
        LocalDateTime withdrawalTime,
        String uuid
    ) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.withdrawalTime = withdrawalTime;
        this.uuid = uuid;
    }
}
