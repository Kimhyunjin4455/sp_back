package starbucks3355.starbucksServer.auth.service;

import starbucks3355.starbucksServer.auth.dto.request.EmailCheckRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.OAuthSignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.SignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.SignUpRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.UserIdCheckRequestDto;
import starbucks3355.starbucksServer.auth.dto.response.EmailCheckResponseDto;
import starbucks3355.starbucksServer.auth.dto.response.OAuthSignInResponseDto;
import starbucks3355.starbucksServer.auth.dto.response.SignInResponseDto;
import starbucks3355.starbucksServer.auth.dto.response.UserIdCheckResponseDto;
import starbucks3355.starbucksServer.auth.vo.request.UserIdCheckRequestVo;

public interface AuthService {

	/**
	 * AuthUserDetail service interface
	 * 1. signUp
	 * 1.1 isEmailDuplicated
	 * 2. signIn
	 * 3. signOut
	 * 4. OAuthSignIn
	 */

	/**
	 * 1. Sign up
	 * Save user
	 * @param signUpRequestDto
	 * return void
	 */
	void signUp(SignUpRequestDto signUpRequestDto);

	/**
	 * 1-1. isEmailDuplicated
	 * 이메일 중복 체크
	 * @param emailCheckRequestDto
	 * return message
	 */
	EmailCheckResponseDto checkEmail(EmailCheckRequestDto emailCheckRequestDto);

	/**
	 * 1-2. isuserIdDuplicated
	 * 아이디 중복 체크
	 * @param userIdCheckRequestDto
	 * return message
	 */
	UserIdCheckResponseDto checkUserId(UserIdCheckRequestDto userIdCheckRequestDto);

	/**
	 * 2. Sign in
	 * Authenticate user
	 * @param signInRequestDto
	 * return SignInResponseDto
	 */
	SignInResponseDto signIn(SignInRequestDto signInRequestDto);

	/**
	 * 3. Sign out
	 * Invalidate token
	 * @param accessToken
	 * return void
	 */
	void signOut(String accessToken);

	/**
	 * 4. OAuth sign in
	 * Authenticate user
	 * @param oAuthSignInRequestDto
	 * return SignInResponseDto
	 */
	OAuthSignInResponseDto oAuthSignIn(OAuthSignInRequestDto oAuthSignInRequestDto);

}
