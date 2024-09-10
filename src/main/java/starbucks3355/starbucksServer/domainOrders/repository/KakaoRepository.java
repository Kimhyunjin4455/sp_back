package starbucks3355.starbucksServer.domainOrders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import starbucks3355.starbucksServer.domainOrders.entity.KakaoPay;

public interface KakaoRepository extends JpaRepository<KakaoPay, Long> {
}
