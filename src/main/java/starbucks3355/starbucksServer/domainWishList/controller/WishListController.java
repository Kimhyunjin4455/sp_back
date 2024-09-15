package starbucks3355.starbucksServer.domainWishList.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.domainWishList.dto.in.WishListRequestDto;
import starbucks3355.starbucksServer.domainWishList.dto.out.WishListResponseDto;
import starbucks3355.starbucksServer.domainWishList.service.WishListService;
import starbucks3355.starbucksServer.domainWishList.vo.in.WishListRequestVo;
import starbucks3355.starbucksServer.domainWishList.vo.out.WishListResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/wishList")
@Tag(name = "WishList", description = "장바구니 API")
public class WishListController {
	private final WishListService wishListService;

	@GetMapping("/wishlist/{memberUuid}")
	@Operation(summary = " 나의 상품 장바구니 조회")
	public CommonResponseEntity<List<WishListResponseVo>> getMyWishList(
		@PathVariable String memberUuid) {
		List<WishListResponseDto> wishListRequestDtoList = wishListService.getMyWishListItems(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			wishListRequestDtoList.stream()
				.map(WishListResponseDto::dtoToResponseVo)
				.toList());

	}

	// 상품 품목의 갯수를 response data로 반환할 Get api
	@GetMapping("/wishlist/{memberUuid}/count")
	@Operation(summary = "나의 상품 장바구니 품목 갯수 조회")
	public CommonResponseEntity<Integer> getMyWishListCount(
		@PathVariable String memberUuid) {
		int count = wishListService.getMyWishListItems(memberUuid).size();

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			count);
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

	@PostMapping("/fromProductDetailsPage/wishlist/{productUuid}/{memberUuid}/add/{quantity}")
	@Operation(summary = "상품 상세페이지에서 장바구니에 n개 추가")
	public CommonResponseEntity<Void> addProductToWishListFromProductDetailsPage(
		@RequestBody WishListRequestVo wishListRequestVo,
		@PathVariable int quantity) {

		WishListRequestDto wishListRequestDto = WishListRequestDto.builder()
			.productUuid(wishListRequestVo.getProductUuid())
			.memberUuid(wishListRequestVo.getMemberUuid())
			.isChecked(true)
			.limitQuantity(wishListRequestVo.getLimitQuantity())
			.currentQuantity(wishListRequestVo.getCurrentQuantity())
			.build();

		log.info("check: {}", wishListRequestDto.isChecked());

		wishListService.addWishListAtProductPage(wishListRequestDto, quantity);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PutMapping("/wishlist/{memberUuid}/{productUuid}/add")
	@Operation(summary = "장바구니의 특정 품목 수량 1 증가")
	public CommonResponseEntity<Void> addProductToWishList(
		@PathVariable String memberUuid,
		@PathVariable String productUuid) {

		wishListService.modifyAddWishList(memberUuid, productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PutMapping("/wishlist/{memberUuid}/{productUuid}/subtract")
	@Operation(summary = "장바구니의 특정 품목 수량 1 감소")
	public CommonResponseEntity<Void> subtractProductFromWishList(
		@PathVariable String memberUuid,
		@PathVariable String productUuid) {

		wishListService.modifySubtractWishList(memberUuid, productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PutMapping("/wishlist/{memberUuid}/{productUuid}/check")
	@Operation(summary = "장바구니의 특정 품목 체크")
	public CommonResponseEntity<Void> checkProductFromWishList(
		@PathVariable String memberUuid,
		@PathVariable String productUuid) {

		wishListService.modifyWishListCheck(memberUuid, productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PutMapping("/wishlist/{memberUuid}/checkAll")
	@Operation(summary = "장바구니 전체 체크")
	public CommonResponseEntity<Void> checkAllProductFromWishList(
		@PathVariable String memberUuid) {

		wishListService.modifyWishListAllSelect(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@DeleteMapping("/wishlist/{memberUuid}/{productUuid}/delete")
	@Operation(summary = "장바구니의 특정 품목 삭제")
	public CommonResponseEntity<Void> deleteProductFromWishList(
		@PathVariable String memberUuid,
		@PathVariable String productUuid) {

		wishListService.deleteWishList(memberUuid, productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@DeleteMapping("/wishlist/{memberUuid}/delete")
	@Operation(summary = "장바구니 전체 삭제")
	public CommonResponseEntity<Void> deleteAllProductFromWishList(
		@PathVariable String memberUuid) {

		wishListService.deleteWishListAll(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@DeleteMapping("/wishlist/{memberUuid}/deleteChecked")
	@Operation(summary = "장바구니 체크된 품목 삭제")
	public CommonResponseEntity<Void> deleteCheckedProductFromWishList(
		@PathVariable String memberUuid) {

		wishListService.deleteWishListChecked(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

}
