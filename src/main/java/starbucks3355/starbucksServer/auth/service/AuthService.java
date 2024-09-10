package starbucks3355.starbucksServer.auth.service;

import starbucks3355.starbucksServer.auth.dto.in.SignInRequestDto;
import starbucks3355.starbucksServer.auth.dto.in.SignUpRequestDto;
import starbucks3355.starbucksServer.auth.dto.out.SignInResponseDto;

public interface AuthService {

	/**
	 * AuthUserDetail service interface
	 * 1. signUp
	 * 2. signIn
	 * 3. signOut
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
}
