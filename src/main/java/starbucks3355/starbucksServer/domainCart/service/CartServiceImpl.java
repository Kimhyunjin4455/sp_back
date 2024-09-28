package starbucks3355.starbucksServer.domainCart.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.exception.BaseException;
import starbucks3355.starbucksServer.domainCart.dto.in.CartRequestDto;
import starbucks3355.starbucksServer.domainCart.dto.out.CartResponseDto;
import starbucks3355.starbucksServer.domainCart.dto.out.TotalInfoResponseDto;
import starbucks3355.starbucksServer.domainCart.entity.Cart;
import starbucks3355.starbucksServer.domainCart.repository.CartRepository;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDefaultDisCount;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDetails;
import starbucks3355.starbucksServer.domainProduct.repository.DiscountRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductDetailsRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	private final CartRepository wishListRepository;

	private final ProductDetailsRepository productDetailsRepository;

	private final DiscountRepository discountRepository;

	@Override
	public List<CartResponseDto> getMyWishListItems(String memberUuid) {
		List<Cart> myCart = wishListRepository.findByMemberUuid(memberUuid);

		if (myCart != null) {
			return myCart.stream()
				.sorted(Comparator.comparing(Cart::getModDate).reversed())
				.map(myCartItem -> CartResponseDto.builder()
					.productUuid(myCartItem.getProductUuid())
					.memberUuid(myCartItem.getMemberUuid())
					.isChecked(
						myCartItem.isChecked()) // Java의 Bean 규약에 따르면, boolean 타입 필드는 is 접두사를 사용하여 getter 메서드가 생성됨
					.limitQuantity(myCartItem.getLimitQuantity())
					.currentQuantity(myCartItem.getCurrentQuantity())
					.regDate(myCartItem.getRegDate())
					.modDate(myCartItem.getModDate())
					.build()).toList();
		}

		return List.of();
	}

	@Override
	public void addWishList(CartRequestDto wishListRequestDto) {

		// wishListRepository.save(
		// 	wishListRequestDto.toEntity(wishListRequestDto.getProductUuid(), wishListRequestDto.getMemberUuid()));
	}

	@Override
	@Transactional // get 이 많으면 위에 선언하고 필요한데만 write로
	public void deleteWishList(String userId, String productUuid) {
		wishListRepository.deleteByMemberUuidAndProductUuid(userId, productUuid);
	}

	@Override
	@Transactional
	public void deleteWishListAll(String userId) {
		wishListRepository.deleteByMemberUuid(userId);

	}

	@Override
	@Transactional
	public void deleteWishListChecked(String memberUuid) {
		List<Cart> carts = wishListRepository.findByMemberUuid(memberUuid);

		carts.stream()
			.filter(Cart::isChecked)
			.forEach(
				cart -> wishListRepository.deleteByMemberUuidAndProductUuid(memberUuid, cart.getProductUuid()));

	}

	@Override
	@Transactional
	public void modifyAddWishList(String memberUuid, String productUuid) {
		wishListRepository.findByMemberUuidAndProductUuid(memberUuid, productUuid)
			.ifPresent(cart -> {
				if (cart.getCurrentQuantity() < cart.getLimitQuantity()) {
					cart.updateCurrentQuantity(cart.getCurrentQuantity() + 1);
					wishListRepository.save(cart);
				} else {
					throw new BaseException(BaseResponseStatus.COUNT_OVER);
				}
			});
	}

	@Override
	@Transactional
	public void modifySubtractWishList(String memberUuid, String productUuid) {
		wishListRepository.findByMemberUuidAndProductUuid(memberUuid, productUuid)
			.ifPresent(cart -> {
				if (cart.getCurrentQuantity() > 1) {
					cart.updateCurrentQuantity(cart.getCurrentQuantity() - 1);
					wishListRepository.save(cart);
				} else {
					throw new BaseException(BaseResponseStatus.COUNT_UNDER_ONE);
				}
			});
	}

	@Override
	@Transactional
	public void modifyWishListCheck(String memberUuid, String productUuid) {

		Optional<Cart> result = wishListRepository.findByMemberUuidAndProductUuid(memberUuid, productUuid);

		if (!result.isPresent()) {
			throw new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT);
		}

		Cart cart = result.get();

		cart.updateChecked(!cart.isChecked());

	}

	@Override
	@Transactional
	public void modifyWishListAllSelect(String memberUuid) {
		List<Cart> carts = wishListRepository.findByMemberUuid(memberUuid);

		// wishLists에 대해 isChecked의 값이 false 인게 하나라도 존재한다면 모든 wishList의 isChecked를 true로 변경
		if (carts.stream().anyMatch(cart -> cart.isChecked() == false)) {
			carts.forEach(cart -> {
				cart.updateChecked(true);
			});
		} else {
			carts.forEach(cart -> {
				cart.updateChecked(false);
			});
		}

	}

	// @Override
	// public void addWishListIsExistProductInWishList(WishListRequestDto wishListRequestDto) {
	// 	List<WishList> wishLists = wishListRepository.findByMemberUuid(wishListRequestDto.getMemberUuid());
	//
	// 	// memberUuid에 대해 productUuid는 최대 20개까지 추가 가능
	// 	if (wishLists.size() >= 20) {
	// 		throw new RuntimeException("하나의 memberUuid에 대해 최대 20개까지 상품을 추가할 수 있습니다.");
	// 	}
	//
	// 	Optional<WishList> existingWishList = wishListRepository.findByMemberUuidAndProductUuid(
	// 		wishListRequestDto.getMemberUuid(),
	// 		wishListRequestDto.getProductUuid());
	//
	// 	if (existingWishList.isPresent()) {
	// 		WishList wishList = existingWishList.get();
	// 		if (wishList.getCurrentQuantity() < wishList.getLimitQuantity()) {
	// 			wishList.updateCurrentQuantity(wishList.getCurrentQuantity() + 1);
	// 			wishListRepository.save(wishList);
	// 		} else {
	// 			throw new RuntimeException("상품의 최대 수량을 초과할 수 없습니다.");
	// 		}
	// 	} else {
	// 		wishListRepository.save(
	// 			wishListRequestDto.toEntity(wishListRequestDto.getProductUuid(), wishListRequestDto.getMemberUuid()));
	// 	}
	// }

	@Override
	public void addWishListAtProductPage(CartRequestDto wishListRequestDto, int quantity) {

		List<Cart> carts = wishListRepository.findByMemberUuid(wishListRequestDto.getMemberUuid());

		ProductDetails detail = productDetailsRepository.findByProductUuid(wishListRequestDto.getProductUuid())
			.orElseThrow(
				() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));

		// memberUuid에 대해 productUuid는 최대 20개까지 추가 가능
		if (carts.size() >= 20) {
			throw new BaseException(BaseResponseStatus.COUNT_OVER_20);
		}

		Optional<Cart> existingWishList = wishListRepository.findByMemberUuidAndProductUuid(
			wishListRequestDto.getMemberUuid(),
			wishListRequestDto.getProductUuid());

		if (existingWishList.isPresent()) {
			Cart cart = existingWishList.get();
			if (cart.getCurrentQuantity() + quantity <= detail.getQuantityLimit()) {
				cart.updateCurrentQuantity(cart.getCurrentQuantity() + quantity);
				wishListRepository.save(cart);
			} else {
				throw new BaseException(BaseResponseStatus.COUNT_OVER);
			}
		} else {
			if (quantity > detail.getQuantityLimit()) {
				throw new BaseException(BaseResponseStatus.COUNT_OVER);
			}
			wishListRequestDto.updateCurrentQuantity(quantity);
			wishListRepository.save(
				wishListRequestDto.toEntity(wishListRequestDto.getProductUuid(), wishListRequestDto.getMemberUuid(),
					detail.getQuantityLimit()));
		}
	}

	@Override
	public TotalInfoResponseDto getWishListTotalPriceAndDiscount(String memberUuid) {
		// 회원의 장바구니 레포지토리에서 is_checked가 true인 상품들을 가져와서 다른 레포지토리에 그 상품에 대한 가격과 할인값을 가져와서 총 계산
		List<Cart> carts = wishListRepository.findByMemberUuid(memberUuid);

		int totalPrice = 0;
		int totalDiscount = 0;

		for (Cart cart : carts) {
			if (cart.isChecked() == true) {
				ProductDetails details = productDetailsRepository.findByProductUuid(cart.getProductUuid())
					.orElseThrow(
						() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));

				int productPrice = details.getProductPrice();

				log.info("productPriceInfo: {}, {}개", productPrice, cart.getCurrentQuantity());
				totalPrice += productPrice * cart.getCurrentQuantity();

				if (discountRepository.findByProductUuid(
					cart.getProductUuid()).isPresent()) {
					ProductDefaultDisCount productDefaultDisCount = discountRepository.findByProductUuid(
						cart.getProductUuid()).get();

					int productDiscount = productDefaultDisCount.getDiscountValue();
					log.info("할인률: {}", productDiscount);
					log.info("할인금액: {}", (productPrice * (productDiscount * 0.01)));
					totalDiscount += (productPrice * (productDiscount * 0.01)) * cart.getCurrentQuantity();
				}

			}
		}

		log.info("totalPrice: {}", totalPrice);
		log.info("totalDiscount: {}", totalDiscount);

		return TotalInfoResponseDto.builder()
			.totalPrice(totalPrice)
			.totalDiscount(totalDiscount)
			.build();
	}

}

