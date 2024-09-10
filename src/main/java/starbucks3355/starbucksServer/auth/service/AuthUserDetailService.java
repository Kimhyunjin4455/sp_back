package starbucks3355.starbucksServer.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;
	@Override
	public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
		return new AuthUserDetail(memberRepository.findByUuid(uuid).orElseThrow(() -> new UsernameNotFoundException(uuid)));
	}
}