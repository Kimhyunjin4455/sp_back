package starbucks3355.starbucksServer.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import starbucks3355.starbucksServer.auth.entity.OAuth;

public interface OAuthRepository extends JpaRepository<OAuth, Long> {
	Optional<OAuth> findByproviderEmail(String providerEmail);
	Optional<OAuth> findByProviderAndProviderId(String provider, String providerId);
}