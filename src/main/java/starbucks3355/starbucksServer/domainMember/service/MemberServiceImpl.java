package starbucks3355.starbucksServer.domainMember.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainMember.dto.requestDto.MemberRequestDto;
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public Member signUpMember(MemberRequestDto memberRequestDto) {

		//중복 검사
		if (memberRepository.existsByuserId(memberRequestDto.getUserId())) {
			throw new RuntimeException("이미 존재하는 아이디입니다.");
		}

		//Member 엔티티로 변환 후 저장
		Member member = memberRequestDto.toEntity(memberRequestDto);
		return memberRepository.save(member);

	}

}


