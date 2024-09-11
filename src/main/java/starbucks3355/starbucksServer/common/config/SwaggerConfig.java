package starbucks3355.starbucksServer.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
	info = @Info(
		title = "삼삼오오 API 명세서",
		description = "스타벅스 리빌딩 프로젝트",
		version = "v1.0"
	)
)
@SecurityScheme(
	name = "Bearer Auth",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "bearer"
)
@Profile("!prod")
public class SwaggerConfig {
	@Bean
	public GroupedOpenApi MemberAPI() {
		return GroupedOpenApi.builder()
			.group("회원")
			.pathsToMatch("/api/v1/member/**")
			.build();
	}

	@Bean
	public GroupedOpenApi AuthAPI() {
		return GroupedOpenApi.builder()
			.group("권한")
			.pathsToMatch("/api/v1/auth/**")
			.build();
	}

	@Bean
	public GroupedOpenApi OrdersAPI() {
		return GroupedOpenApi.builder()
			.group("주문")
			.pathsToMatch("/api/v1/orders/**")
			.build();
	}

	@Bean
	public GroupedOpenApi ProductAPI() {
		return GroupedOpenApi.builder()
			.group("상품")
			.pathsToMatch("/api/v1/product/**")
			.build();
	}

	@Bean
	public GroupedOpenApi CategoryAPI() {
		return GroupedOpenApi.builder()
			.group("카테고리")
			.pathsToMatch("/api/v1/category/**")
			.build();
	}

	@Bean
	public GroupedOpenApi ImageAPI() {
		return GroupedOpenApi.builder()
			.group("이미지")
			.pathsToMatch("/api/v1/image/**")
			.build();
	}

	@Bean
	GroupedOpenApi ReviewAPI() {
		return GroupedOpenApi.builder()
			.group("리뷰")
			.pathsToMatch("/api/v1/review/**")
			.build();
	}

	@Bean
	GroupedOpenApi KakaoAPI() {
		return GroupedOpenApi.builder()
			.group("카카오페이")
			.pathsToMatch("/api/v1/kakao/**")
			.build();
	}

}
