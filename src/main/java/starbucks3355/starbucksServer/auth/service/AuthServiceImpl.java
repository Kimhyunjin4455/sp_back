package starbucks3355.starbucksServer.auth.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.dto.request.SignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.SignUpRequestDto;
import starbucks3355.starbucksServer.auth.dto.response.SignInResponseDto;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.exception.BaseException;
import starbucks3355.starbucksServer.common.jwt.JwtTokenProvider;
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

	private final MemberRepository memberRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	/**
	 * AuthServiceImpl
	 * 1. 회원가입
	 * 2. 로그인
	 * 3. 로그아웃
	 */

	@Override
	@Transactional
	public void signUp(SignUpRequestDto signUpRequestDto) {

		log.info("signUpRequestDto : {}", signUpRequestDto);

		try {
			memberRepository.save(signUpRequestDto.toEntity(passwordEncoder));
		} catch (Exception e) {
			throw new BaseException(BaseResponseStatus.FAILED_TO_RESTORE);
		}

	}

	@Override
	@Transactional
	public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {

		log.info("signInRequestDto : {}", signInRequestDto);
		Member member = memberRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(
			() -> new BaseException(BaseResponseStatus.FAILED_TO_LOGIN)
		);

		try
		{
			String token = createToken(authenticate(member, signInRequestDto.getPassword()));
			// log.info("token : {}", token);

			return SignInResponseDto.builder()
				.accessToken(token)
				.uuid(member.getUuid())
				.name(member.getName())
				.build();

		} catch (Exception e) {
			throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
		}

	}

	private String createToken(Authentication authentication) {
		return jwtTokenProvider.generateAccessToken(authentication);
	}

	private Authentication authenticate(Member member, String inputPassword) {
		AuthUserDetail authUserDetail = new AuthUserDetail(member);
		return authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				authUserDetail.getUsername(),
				inputPassword
			)
		);
	}

}
