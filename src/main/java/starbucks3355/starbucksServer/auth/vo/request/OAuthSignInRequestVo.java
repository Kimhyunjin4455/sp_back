package starbucks3355.starbucksServer.auth.vo.request;

import lombok.Getter;

@Getter
public class OAuthSignInRequestVo {

	private String provider;
	private String providerId;
	private String providerEmail;

}