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
	private String email;

	public AuthUserDetail(Member member) {
		this.uuid = member.getUuid();
		this.password = member.getPassword();
		this.email = member.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.uuid;
	}

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

