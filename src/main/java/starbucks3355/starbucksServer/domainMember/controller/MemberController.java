package starbucks3355.starbucksServer.domainMember.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainMember.dto.requestDto.MemberRequestDto;
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainMember.service.MemberService;
import starbucks3355.starbucksServer.domainMember.vo.requestVo.MemberRequestVo;

@RestController
@RequestMapping("/api/v1/member/signup")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping
	//vo의 값을 Dto로 변환
	// 변환된 값을 Service로 넘겨줌
	public ResponseEntity<Member> signUpMember(MemberRequestVo memberRequestVo) {
		MemberRequestDto memberRequestDto = new MemberRequestDto(
			memberRequestVo.getUserId(),
			memberRequestVo.getPassword(),
			memberRequestVo.getEmail(),
			memberRequestVo.getPhoneNumber(),
			memberRequestVo.getBirth(),
			memberRequestVo.getAddress(),
			memberRequestVo.getGender(),
			memberRequestVo.getNickname(),
			memberRequestVo.getMemberUuid(),
			memberRequestVo.getIsMember(),
			memberRequestVo.getWithdrawalTime()
		);
		memberService.signUpMember(memberRequestDto);
		return new ResponseEntity<Member>(HttpStatus.CREATED);
	}

}
