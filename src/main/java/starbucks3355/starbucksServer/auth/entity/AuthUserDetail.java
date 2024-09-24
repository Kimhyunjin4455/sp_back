package starbucks3355.starbucksServer.auth.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainMember.entity.Member;

@ToString
@Slf4j
@Getter
@NoArgsConstructor
public class AuthUserDetail implements UserDetails {

	private String uuid;
	private String password;
	private String userId;

	private String nickname;
	private String email;

	public AuthUserDetail(Member member) {
		this.uuid = member.getUuid();
		this.password = member.getPassword();
		this.userId = member.getUserId();
		this.nickname = member.getNickname();
		this.email = member.getEmail();
		this.userId = member.getUserId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() { return this.password; }

	@Override
	public String getUsername() {
		return this.uuid;
	}

	public String getNickname() { return this.nickname; }
	public String getEmail() { return this.email; }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

