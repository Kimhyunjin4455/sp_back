package starbucks3355.starbucksServer.auth.service;

import java.util.Optional;

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
import starbucks3355.starbucksServer.auth.dto.request.EmailCheckRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.OAuthSignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.SignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.SignUpRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.UserIdCheckRequestDto;
import starbucks3355.starbucksServer.auth.dto.response.EmailCheckResponseDto;
import starbucks3355.starbucksServer.auth.dto.response.OAuthSignInResponseDto;
import starbucks3355.starbucksServer.auth.dto.response.SignInResponseDto;
import starbucks3355.starbucksServer.auth.dto.response.UserIdCheckResponseDto;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.auth.repository.OAuthRepository;
import starbucks3355.starbucksServer.auth.vo.request.UserIdCheckRequestVo;
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
	private final OAuthRepository oAuthRepository;
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

		try {
			memberRepository.save(signUpRequestDto.toEntity(passwordEncoder));
		} catch (Exception e) {
			throw new BaseException(BaseResponseStatus.FAILED_TO_RESTORE);
		}

	}

	@Override
	public EmailCheckResponseDto checkEmail(EmailCheckRequestDto emailCheckRequestDto) {
		boolean isDuplicated = memberRepository.findByEmail(emailCheckRequestDto.getEmail()).isPresent();
		String message = isDuplicated ? "이미 존재하는 이메일입니다.":"사용 가능한 이메일입니다.";

		return EmailCheckResponseDto.builder()
			.isDuplicated(isDuplicated)
			.message(message)
			.build();
	}

	@Override
	public UserIdCheckResponseDto checkUserId(UserIdCheckRequestDto userIdCheckRequestDto) {
		boolean isDuplicated = memberRepository.findByUserId(userIdCheckRequestDto.getUserId()).isPresent();
		String message = isDuplicated ? "이미 존재하는 아이디입니다.":"사용 가능한 아이디입니다.";

		return UserIdCheckResponseDto.builder()
			.isDuplicated(isDuplicated)
			.message(message)
			.build();
	}

	@Override
	@Transactional
	public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {

		Member member = memberRepository.findByUserId(signInRequestDto.getUserId()).orElseThrow(
			() -> new BaseException(BaseResponseStatus.FAILED_TO_LOGIN)
		);

		try
		{
			String token = createToken(authenticate(member, signInRequestDto.getPassword()));
			return SignInResponseDto.from(member, token);

		} catch (Exception e) {
			throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
		}

	}

	@Override
	public void signOut(String accessToken) {

	}


	@Override
	public OAuthSignInResponseDto oAuthSignIn(OAuthSignInRequestDto oAuthSignInRequestDto) {

		Optional<Member> member = memberRepository.findByEmail(oAuthSignInRequestDto.getProviderEmail());


		if (member.isEmpty()) {
			// oAuthRepository.save(oAuthSignInRequestDto.toEntity(member.get().getUuid()));
			return OAuthSignInResponseDto.from(false);
		}

		String token = createToken(oAuthAuthenticate(member.get().getEmail()));
		log.info("token : {}", token);

		return OAuthSignInResponseDto.from(member.get(), token, true);

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

	private Authentication oAuthAuthenticate(String email) {
		Member member = memberRepository.findByEmail(email).get();
		log.info("email : {}", email);
		return authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				email, null
			)
		);
	}

}