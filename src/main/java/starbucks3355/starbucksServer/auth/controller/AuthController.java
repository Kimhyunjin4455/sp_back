package starbucks3355.starbucksServer.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.dto.request.EmailCheckRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.OAuthSignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.SignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.SignUpRequestDto;
import starbucks3355.starbucksServer.auth.dto.response.EmailCheckResponseDto;
import starbucks3355.starbucksServer.auth.service.AuthService;
import starbucks3355.starbucksServer.auth.vo.request.OAuthSignInRequestVo;
import starbucks3355.starbucksServer.auth.vo.request.SignInRequestVo;
import starbucks3355.starbucksServer.auth.vo.request.SignUpRequestVo;
import starbucks3355.starbucksServer.auth.vo.response.SignInResponseVo;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;

	/**
	 * api/v1/auth
	 * 1. 회원가입
	 * 1-1. 회원가입 시 이메일 중복 처리
	 * 2. 로그인
	 * 3. 로그아웃
	 * 4. 소셜로그인
	 */


	/**
	 * 회원가입
	 * @param signUpRequestVo SignUpRequestVo
	 * @return BaseResponse<Void>
	 */
	@Operation(summary = "SignUp API", description = "SignUp API 입니다.", tags = {"AuthUserDetail"})
	@PostMapping("/sign-up")
	public BaseResponse<Void> signUp(
		@RequestBody SignUpRequestVo signUpRequestVo) {
		log.info("signUpRequestVo : {}", signUpRequestVo);
		authService.signUp(SignUpRequestDto.from(signUpRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "이메일 중복 여부 확인 API", description = "이메일 중복 여부 확인 API 입니다.", tags = {"AuthUserDetail"})
	@PostMapping("/email-duplicate")
	public CommonResponseEntity<EmailCheckResponseDto> checkEmail(@RequestBody EmailCheckRequestDto emailCheckRequestDto) {
		EmailCheckResponseDto response = authService.checkEmail(emailCheckRequestDto);
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			response);
	}


	@Operation(summary = "SignIn API", description = "SignIn API 입니다.", tags = {"AuthUserDetail"})
	@PostMapping("/sign-in")
	public BaseResponse<SignInResponseVo> signIn(
		@RequestBody SignInRequestVo signInRequestVo) {

		return new BaseResponse<>(
			authService.signIn(SignInRequestDto.from(signInRequestVo)).toVo()
		);
	}

	/**
	 * 소셜로그인
	 * @param oAuthSignInRequestVo OAuthSignInRequestVo
	 * @return BaseResponse<SignInResponseVo>
	 */
	@Operation(summary = "OAuth SignIn API", description = "OAuth SignIn API 입니다.", tags = {"AuthUserDetail"})
	@PostMapping("/oauth-sign-in")
	public BaseResponse<SignInResponseVo> oAuthSignIn(
		@RequestBody OAuthSignInRequestVo oAuthSignInRequestVo) {

		return new BaseResponse<>(
			authService.oAuthSignIn(OAuthSignInRequestDto.of(oAuthSignInRequestVo)).toVo()
		);
	}



}