package starbucks3355.starbucksServer.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.dto.in.SignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.in.SignUpRequestDto;
import starbucks3355.starbucksServer.auth.service.AuthService;
import starbucks3355.starbucksServer.auth.vo.in.SignInRequestVo;
import starbucks3355.starbucksServer.auth.vo.in.SignUpRequestVo;
import starbucks3355.starbucksServer.auth.vo.out.SignInResponseVo;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;

	/**
	 * api/v1/auth
	 * 1. 회원가입
	 * 2. 로그인
	 * 3. 로그아웃
	 */


	@Operation(summary = "SignUp API", description = "SignUp API", tags = {"AuthUserDetail"})
	@PostMapping("/sign-up")
	public BaseResponse<Void> signUp(
		@RequestBody SignUpRequestVo signUpRequestVo) {
		log.info("signUpRequestVo : {}", signUpRequestVo);
		authService.signUp(SignUpRequestDto.from(signUpRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "SignIn API", description = "SignIn API", tags = {"AuthUserDetail"})
	@PostMapping("/sign-in")
	public BaseResponse<SignInResponseVo> signIn(
		@RequestBody SignInRequestVo signInRequestVo) {

		return new BaseResponse<>(
			authService.signIn(SignInRequestDto.from(signInRequestVo)).toVo()
		);
	}


}
