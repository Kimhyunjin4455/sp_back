package starbucks3355.starbucksServer.domainMember.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainMember.dto.requestDto.LoginRequestDto;
import starbucks3355.starbucksServer.domainMember.dto.requestDto.MemberRequestDto;
import starbucks3355.starbucksServer.domainMember.dto.reseponseDto.LoginResponseDto;
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public Member signUpMember(MemberRequestDto memberRequestDto) {
		//중복 검사
		if (memberRepository.existsByUserId(memberRequestDto.getUserId())) {
			throw new RuntimeException("이미 존재하는 아이디입니다.");
		}

		//Member 엔티티로 변환 후 저장
		Member member = memberRequestDto.toEntity(memberRequestDto);
		return memberRepository.save(member);

	}

	@Override
	public LoginResponseDto loginMember(LoginRequestDto loginRequestDto) {
		// 사용자 ID로 회원 조회
		Member member = memberRepository.findByUserId(loginRequestDto.getUserId());

		// 비밀번호 확인
		if (member != null && !member.getPassword().equals(loginRequestDto.getPassword())) {
			throw new RuntimeException("로그인에 실패하였습니다.");
		}

		return new LoginResponseDto(member.getUserId());
	}

	@Override
	public void logoutMember(HttpSession session) {
		// 세션 무효화
		session.invalidate();
		log.info("로그아웃 성공");
	}

}


