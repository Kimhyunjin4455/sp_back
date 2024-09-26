package starbucks3355.starbucksServer.auth.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.dto.request.EmailCheckRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.FindPasswordRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.FindUserIdRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.OAuthSignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.SignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.SignUpRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.UpdatePasswordRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.UserIdCheckRequestDto;
import starbucks3355.starbucksServer.auth.dto.response.EmailCheckResponseDto;
import starbucks3355.starbucksServer.auth.dto.response.FindPasswordResponseDto;
import starbucks3355.starbucksServer.auth.dto.response.FindUserIdResponseDto;
import starbucks3355.starbucksServer.auth.dto.response.OAuthSignInResponseDto;
import starbucks3355.starbucksServer.auth.dto.response.SignInResponseDto;
import starbucks3355.starbucksServer.auth.dto.response.UserIdCheckResponseDto;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.auth.repository.OAuthRepository;
import starbucks3355.starbucksServer.auth.vo.request.UpdatePasswordRequestVo;
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

	/**
	 * signUp
	 */
	@Override
	@Transactional
	public void signUp(SignUpRequestDto signUpRequestDto) {

		// 이메일 중복 체크
		if (memberRepository.findByEmail(signUpRequestDto.getEmail()).isPresent()) {
			throw new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
		}

		// 아이디 중복 체크
		if (memberRepository.findByUserId(signUpRequestDto.getUserId()).isPresent()) {
			throw new BaseException(BaseResponseStatus.DUPLICATED_USER);
		}
		try {
			memberRepository.save(signUpRequestDto.toEntity(passwordEncoder));
		} catch (Exception e) {
			throw new BaseException(BaseResponseStatus.FAILED_TO_RESTORE);
		}

	}

	/**
	 * checkEmail
	 */
	@Override
	public EmailCheckResponseDto checkEmail(EmailCheckRequestDto emailCheckRequestDto) {
		boolean isDuplicated = memberRepository.findByEmail(emailCheckRequestDto.getEmail()).isPresent();
		String message = isDuplicated ? "이미 존재하는 이메일입니다.":"사용 가능한 이메일입니다.";

		return EmailCheckResponseDto.builder()
			.isDuplicated(isDuplicated)
			.message(message)
			.build();
	}

	/**
	 * checkUserId
	 */
	@Override
	public UserIdCheckResponseDto checkUserId(UserIdCheckRequestDto userIdCheckRequestDto) {
		boolean isDuplicated = memberRepository.findByUserId(userIdCheckRequestDto.getUserId()).isPresent();
		String message = isDuplicated ? "이미 존재하는 아이디입니다.":"사용 가능한 아이디입니다.";

		return UserIdCheckResponseDto.builder()
			.isDuplicated(isDuplicated)
			.message(message)
			.build();
	}

	/**
	 * signIn
	 */
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

	/**
	 * signOut
	 */
	@Override
	public void signOut(String accessToken) {

	}


	/**
	 * oAuthSignIn
	 */
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

	/**
	 * findUserId
	 */
	@Override
	public FindUserIdResponseDto findUserId(FindUserIdRequestDto findUserIdRequestDto) {
		String email = findUserIdRequestDto.getEmail(); // 프론트에서 받은 이메일


		// 이메일로 사용자를 찾기
		Optional<Member> memberOptional = memberRepository.findByEmail(email);

		if (memberOptional.isPresent()) {
			String userId = memberOptional.get().getUserId();
			String maskedUserId = maskUserId(userId); // 아이디 마스킹
			return FindUserIdResponseDto.builder()
				.userId(maskedUserId)
				.message("아이디를 성공적으로 찾았습니다.")
				.build();
		}

		return FindUserIdResponseDto.builder()
			.userId(null)
			.message("해당 이메일에 대한 아이디가 존재하지 않습니다.")
			.build();
	}

	// 아이디 마스킹 메서드
	private String maskUserId(String userId) {
		if (userId.length() <= 3) {
			return userId; // 아이디가 3자 이하인 경우 마스킹 처리하지 않음
		}
		String maskedPart = userId.substring(0, userId.length() - 3);
		String visiblePart = userId.substring(userId.length() - 3);
		return maskedPart + "***"; // 끝 3자만 보이게 마스킹
	}

	/**
	 * findPassword (이메일, 아이디로 회원 정보 있는지 찾기)
	 */
	@Override
	public FindPasswordResponseDto findPassword(FindPasswordRequestDto findPasswordRequestDto) {
		String userId = findPasswordRequestDto.getUserId();
		String email =  findPasswordRequestDto.getEmail();

		Optional<Member> memberOptional = memberRepository.findByUserIdAndEmail(userId, email);

		if (memberOptional.isPresent()) {
			Member member = memberOptional.get();

			String accessToken = jwtTokenProvider.generateAccessTokenByFindPw(
				member.getUuid());

			return FindPasswordResponseDto.builder()
				.isUser(true)
				.accessToken(accessToken)
				.nickname(member.getNickname())
				.build();
		}
		return FindPasswordResponseDto.builder()
			.isUser(false)
			.build();
	}



	/**
	 * updatePassword (비밀번호 수정)
	 */
	@Override
	@Transactional
	public void updatePassword(String uuid, UpdatePasswordRequestDto updatePasswordRequestDto) {
		if (uuid == null || uuid.isEmpty()) {
			throw new BaseException(BaseResponseStatus.NO_EXIST_USER); // UUID가 없을 경우 처리
		}

		Member member = memberRepository.findByUuid(uuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

		member.updatePassword(passwordEncoder.encode(updatePasswordRequestDto.getNewPassword()));
		memberRepository.save(member);
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