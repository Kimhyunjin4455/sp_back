package starbucks3355.starbucksServer.common.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.auth.service.AuthUserDetailService;
import starbucks3355.starbucksServer.auth.service.OAuthUserDetailService;
import starbucks3355.starbucksServer.common.provider.OAuthAuthenticationProvider;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {

	private final AuthUserDetailService authUserDetailService;
	private final OAuthAuthenticationProvider oAuthAuthenticationProvider;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(authUserDetailService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
		throws Exception {
		return new ProviderManager(Arrays.asList(
			authenticationProvider(),
			oAuthAuthenticationProvider
		));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
