package starbucks3355.starbucksServer.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.exception.BaseException;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class OAuthUserDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new AuthUserDetail(memberRepository.findByEmail(email).orElseThrow(
			() -> new BaseException(BaseResponseStatus.NO_EXIST_USER)
		));
	}
}