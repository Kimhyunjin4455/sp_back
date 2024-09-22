package starbucks3355.starbucksServer.domainCart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.domainCart.dto.in.CartRequestDto;
import starbucks3355.starbucksServer.domainCart.dto.out.CartResponseDto;
import starbucks3355.starbucksServer.domainCart.dto.out.TotalInfoResponseDto;
import starbucks3355.starbucksServer.domainCart.service.CartService;
import starbucks3355.starbucksServer.domainCart.vo.in.CartRequestVo;
import starbucks3355.starbucksServer.domainCart.vo.out.CartResponseVo;
import starbucks3355.starbucksServer.domainCart.vo.out.TotalInfoResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/wishList")
@Tag(name = "WishList", description = "장바구니 API")
public class CartController {
	private final CartService wishListService;

	@GetMapping("/view")
	@Operation(summary = " 나의 상품 장바구니 조회")
	public CommonResponseEntity<List<CartResponseVo>> getMyWishList(
		@AuthenticationPrincipal AuthUserDetail authUserDetail
	) {
		String memberUuid = authUserDetail.getUuid(); // 로그인된 사용자의 UUID 가져오기

		List<CartResponseDto> wishListRequestDtoList = wishListService.getMyWishListItems(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			wishListRequestDtoList.stream()
				.map(CartResponseDto::dtoToResponseVo)
				.toList());

	}

	// 상품 품목의 갯수를 response data로 반환할 Get api
	@GetMapping("/itemCount")
	@Operation(summary = "나의 상품 장바구니 품목 갯수 조회")
	public CommonResponseEntity<Integer> getMyWishListCount(
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {

		String memberUuid = authUserDetail.getUuid(); // 로그인된 사용자의 UUID 가져오기

		int count = wishListService.getMyWishListItems(memberUuid).size();

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			count);
	}

	@GetMapping("/totalPriceAndDiscount")
	@Operation(summary = "나의 상품 장바구니 품목의 총 가격, 총 할인액 조회")
	public CommonResponseEntity<TotalInfoResponseVo> getMyWishListTotalInfo(
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {

		String memberUuid = authUserDetail.getUuid(); // 로그인된 사용자의 UUID 가져오기

		TotalInfoResponseDto totalInfoResponseDto = wishListService.getWishListTotalPriceAndDiscount(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			totalInfoResponseDto.dtoToResponseVo());
	}

	// @PostMapping("/wishlist/{productUuid}/{memberUuid}/add")
	// @Operation(summary = "상품 장바구니 추가")
	// public CommonResponseEntity<Void> addProductToWishList(
	// 	@RequestBody WishListRequestVo wishListRequestVo) {
	//
	// 	WishListRequestDto wishListRequestDto = WishListRequestDto.builder()
	// 		.productUuid(wishListRequestVo.getProductUuid())
	// 		.memberUuid(wishListRequestVo.getMemberUuid())
	// 		.isChecked(wishListRequestVo.isChecked())
	// 		.limitQuantity(wishListRequestVo.getLimitQuantity())
	// 		.currentQuantity(wishListRequestVo.getCurrentQuantity())
	// 		.build();
	//
	// 	wishListService.addWishListIsExistProductInWishList(wishListRequestDto);
	//
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		null);
	// }

	@PostMapping("/fromProductDetailsPage/wishlist/{productUuid}/add/{quantity}")
	@Operation(summary = "상품 상세페이지에서 장바구니에 n개 추가")
	public CommonResponseEntity<Void> addProductToWishListFromProductDetailsPage(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@RequestBody CartRequestVo wishListRequestVo,
		@PathVariable int quantity) {

		String memberUuid = authUserDetail.getUuid();

		CartRequestDto wishListRequestDto = CartRequestDto.builder()
			.productUuid(wishListRequestVo.getProductUuid())
			.memberUuid(memberUuid)
			.isChecked(true)
			.limitQuantity(wishListRequestVo.getLimitQuantity())
			.build();

		// 문제 발생: quantity가 limitQuantity보다 커도 추가됨 -> 예외 발생 필요
		if (quantity > wishListRequestVo.getLimitQuantity()) {
			throw new IllegalArgumentException("quantity가 limitQuantity보다 큽니다.");
		}

		log.info("check: {}", wishListRequestDto.isChecked());

		wishListService.addWishListAtProductPage(wishListRequestDto, quantity);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PutMapping("/itemQuantity/{productUuid}/add")
	@Operation(summary = "장바구니의 특정 품목 수량 1 증가")
	public CommonResponseEntity<Void> addProductToWishList(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable String productUuid) {

		String memberUuid = authUserDetail.getUuid();

		wishListService.modifyAddWishList(memberUuid, productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PutMapping("/itemQuantity/{productUuid}/subtract")
	@Operation(summary = "장바구니의 특정 품목 수량 1 감소")
	public CommonResponseEntity<Void> subtractProductFromWishList(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable String productUuid) {

		String memberUuid = authUserDetail.getUuid();

		wishListService.modifySubtractWishList(memberUuid, productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PutMapping("/{productUuid}/check")
	@Operation(summary = "장바구니의 특정 품목 체크")
	public CommonResponseEntity<Void> checkProductFromWishList(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable String productUuid) {

		String memberUuid = authUserDetail.getUuid();

		wishListService.modifyWishListCheck(memberUuid, productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PutMapping("/checkAll")
	@Operation(summary = "장바구니 전체 체크")
	public CommonResponseEntity<Void> checkAllProductFromWishList(
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {

		String memberUuid = authUserDetail.getUuid();

		wishListService.modifyWishListAllSelect(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@DeleteMapping("/{productUuid}/deleteWishListItem")
	@Operation(summary = "장바구니의 특정 품목 삭제")
	public CommonResponseEntity<Void> deleteProductFromWishList(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable String productUuid) {

		String memberUuid = authUserDetail.getUuid();

		wishListService.deleteWishList(memberUuid, productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@DeleteMapping("/deleteAll")
	@Operation(summary = "장바구니 전체 삭제")
	public CommonResponseEntity<Void> deleteAllProductFromWishList(
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {

		String memberUuid = authUserDetail.getUuid();

		wishListService.deleteWishListAll(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@DeleteMapping("/deleteChecked")
	@Operation(summary = "장바구니 체크된 품목 삭제")
	public CommonResponseEntity<Void> deleteCheckedProductFromWishList(
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {

		String memberUuid = authUserDetail.getUuid();

		wishListService.deleteWishListChecked(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

}
