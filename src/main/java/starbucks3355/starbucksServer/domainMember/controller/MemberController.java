package starbucks3355.starbucksServer.domainMember.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.CustomLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.common.entity.CommonResponseSliceEntity;
import starbucks3355.starbucksServer.common.jwt.JwtTokenProvider;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainMember.dto.LikeStatusResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.LikesProductResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberReviewResponseDto;
import starbucks3355.starbucksServer.domainMember.service.MemberService;
import starbucks3355.starbucksServer.domainMember.vo.LikesProductResponseVo;
import starbucks3355.starbucksServer.domainMember.vo.MemberInfoResponseVo;
import starbucks3355.starbucksServer.domainMember.vo.MemberReviewResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

	private final MemberService memberService;
	private final JwtTokenProvider provider;

	/**
	 * api/v1/member
	 * 1. 회원 정보 조회
	 * 2. 찜하기
	 * 3. 찜한 상품 조회
	 * 4. 상품 uuid 조회 시 찜 여부 파악
	 */

	/**
	 * 회원 정보 조회
	 * @param authUserDetail
	 * @return
	 */

	@Operation(summary = "Member Get Info API", description = "Member 정보 조회 API", tags = {"Member"})
	@SecurityRequirement(name = "Bearer Auth")
	@GetMapping
	public BaseResponse<MemberInfoResponseVo> getMemberInfo(
		@AuthenticationPrincipal AuthUserDetail authUserDetail
	) {
		log.info("authUserDetail : {}", authUserDetail);
		log.info("authUserDetail.getUername() : {}", authUserDetail.getUsername());
		return new BaseResponse<>(
			memberService.getMemberInfo(authUserDetail.getUsername()
			).toVo()
		);
	}

	@PostMapping("/likes")
	@Operation(summary = "찜하기, 찜하기 취소")
	public BaseResponse<Void> likeProduct(@RequestHeader("Authorization") String accessToken,
		String productUuid) {
		String uuid = provider.parseUuid(accessToken);

		if (uuid == null) {
			return new BaseResponse<>(
				BaseResponseStatus.NO_SIGN_IN.getHttpStatusCode(), // HTTP 상태 코드
				BaseResponseStatus.NO_SIGN_IN.isSuccess(), // 성공 여부
				BaseResponseStatus.NO_SIGN_IN.getMessage(), // 메시지
				BaseResponseStatus.NO_SIGN_IN.getCode(),
				null
			); // UUID가 없을 경우
		}

		memberService.LikeStatus(uuid, productUuid);
		return new BaseResponse<>();
	}

	@GetMapping("/likeslist")
	@Operation(summary = "찜한 상품 목록 조회")
	public BaseResponse<CursorPage<String>> getLikes(
		@RequestHeader("Authorization") String accessToken,
		@RequestParam(value = "lastId", required = false) Long lastId,
		@RequestParam(value = "pageSize", required = false) Integer pageSize,
		@RequestParam(value = "page", required = false) Integer page
	) {

		String userUuid = provider.parseUuid(accessToken);

		CursorPage<String> likesProductResponseDtos = memberService.getLikesList(userUuid, lastId, pageSize, page);
		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			likesProductResponseDtos
		);
	}

	@GetMapping("/{productUuid}/like-status")
	@Operation(summary = "상품의 찜 여부 조회", description = "productUuid를 통해 찜 여부 확인")
	public BaseResponse<LikeStatusResponseDto> checkLikeStatus(
		@RequestHeader("Authorization") String accessToken,
		@PathVariable String productUuid) {

		String uuid = provider.parseUuid(accessToken);

		if (uuid == null) {
			return new BaseResponse<>(
				BaseResponseStatus.NO_SIGN_IN.getHttpStatusCode(),
				BaseResponseStatus.NO_SIGN_IN.isSuccess(),
				BaseResponseStatus.NO_SIGN_IN.getMessage(),
				BaseResponseStatus.NO_SIGN_IN.getCode(),
				null
			);
		}

		// 찜 여부 확인
		boolean isLiked = memberService.checkLikeStatus(uuid, productUuid).isLiked();

		// LikesProductResponseDto 생성
		LikeStatusResponseDto response = LikeStatusResponseDto.builder()
			.isLiked(isLiked)
			.build();

		// 찜 여부만 포함된 응답 반환
		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			response // 찜 여부만 반환
		);
	}
}
