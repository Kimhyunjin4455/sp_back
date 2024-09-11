package starbucks3355.starbucksServer.common.jwt;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.service.AuthUserDetailService;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	//    todo JwtTokenProvider
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthUserDetailService userDetailsService;

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String uuid;
		log.info("authheader : {}",authHeader);

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		jwt = authHeader.substring(7);

		uuid = Jwts.parser().verifyWith((SecretKey) jwtTokenProvider.getSignKey())
			.build().parseSignedClaims(jwt).getPayload().get("uuid", String.class);

		log.info("uuid: {}", uuid);

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(uuid);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails,
				null,
				userDetails.getAuthorities()
			);
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
	}
}
