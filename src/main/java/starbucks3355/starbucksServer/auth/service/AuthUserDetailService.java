package starbucks3355.starbucksServer.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;
	@Override
	public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
		log.info("@@@@@@ : {}", uuid);
		return new AuthUserDetail(memberRepository.findByUuid(uuid).orElseThrow(() -> new UsernameNotFoundException(uuid)));
	}
}