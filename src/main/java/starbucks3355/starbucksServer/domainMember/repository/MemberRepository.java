package starbucks3355.starbucksServer.domainMember.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import starbucks3355.starbucksServer.domainMember.entity.Member;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);
	Optional<Member> findByUuid(String uuid);

}
