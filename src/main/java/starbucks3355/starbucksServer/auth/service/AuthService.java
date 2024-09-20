package starbucks3355.starbucksServer.auth.service;

import starbucks3355.starbucksServer.auth.dto.request.OAuthSignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.SignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.request.SignUpRequestDto;
import starbucks3355.starbucksServer.auth.dto.response.SignInResponseDto;

public interface AuthService {

	/**
	 * AuthUserDetail service interface
	 * 1. signUp
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
	SignInResponseDto oAuthSignIn(OAuthSignInRequestDto oAuthSignInRequestDto);

}
