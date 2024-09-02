package starbucks3355.starbucksServer.commonFile;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
	info = @Info(
		title = "삼삼오오 API 명세서",
		description = "스타벅스 리빌딩 프로젝트",
		version = "v1.0"
	)
)
public class SwaggerConfig {
	@Bean
	public GroupedOpenApi MemberAPI() {
		return GroupedOpenApi.builder()
			.group("회원")
			.pathsToMatch("/api/v1/member/**")
			.build();
	}

	@Bean
	public GroupedOpenApi OrdersAPI() {
		return GroupedOpenApi.builder()
			.group("주문")
			.pathsToMatch("/api/v1/orders/**")
			.build();
	}

}
