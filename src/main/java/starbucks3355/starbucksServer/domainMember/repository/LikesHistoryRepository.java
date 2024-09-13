package starbucks3355.starbucksServer.domainMember.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import starbucks3355.starbucksServer.domainMember.entity.LikesHistory;

public interface LikesHistoryRepository extends JpaRepository<LikesHistory, Long> {
}
