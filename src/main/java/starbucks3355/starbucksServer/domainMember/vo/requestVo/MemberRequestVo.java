package starbucks3355.starbucksServer.domainMember.vo.requestVo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MemberRequestVo {
	private String userId;
	private String password;
	private String email;
	private String phoneNumber;
	private LocalDate birth;
	private String address;
	private String gender;
	private String nickname;
	private UUID memberUuid;
	private Boolean isMember;
	private LocalDateTime withdrawalTime;

}
