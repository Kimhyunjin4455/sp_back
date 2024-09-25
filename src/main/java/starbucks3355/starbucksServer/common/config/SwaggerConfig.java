package starbucks3355.starbucksServer.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@OpenAPIDefinition(
	info = @Info(
		title = "삼삼오오 API 명세서",
		description = "스타벅스 리빌딩 프로젝트",
		version = "v1.0"
	)
)
@SecurityScheme(
	name = "bearerAuth",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "bearer"
)

public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.addServersItem(new Server().url("/"))
			.components(new Components()
				.addSecuritySchemes("bearerAuth",
					new io.swagger.v3.oas.models.security.SecurityScheme()
						.type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT")))
			.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearerAuth"));
	}

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

	@Bean
	GroupedOpenApi ShippingAPI() {
		return GroupedOpenApi.builder()
			.group("배송지")
			.pathsToMatch("/api/v1/shipping/**")
			.build();
	}

	@Bean
	GroupedOpenApi WishListAPI() {
		return GroupedOpenApi.builder()
			.group("장바구니")
			.pathsToMatch("/api/v1/wishList/**")
			.build();
	}

	@Bean
	GroupedOpenApi CouponAPI() {
		return GroupedOpenApi.builder()
			.group("쿠폰")
			.pathsToMatch("/api/v1/coupon/**")
			.build();
	}

	@Bean
	GroupedOpenApi PromotionAPI() {
		return GroupedOpenApi.builder()
			.group("기획전")
			.pathsToMatch("/api/v1/promotion/**")
			.build();
	}
}
