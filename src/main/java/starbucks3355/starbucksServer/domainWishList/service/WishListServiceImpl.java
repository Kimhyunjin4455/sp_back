package starbucks3355.starbucksServer.domainWishList.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDefaultDisCount;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDetails;
import starbucks3355.starbucksServer.domainProduct.repository.DiscountRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductDetailsRepository;
import starbucks3355.starbucksServer.domainWishList.dto.in.WishListRequestDto;
import starbucks3355.starbucksServer.domainWishList.dto.out.TotalInfoResponseDto;
import starbucks3355.starbucksServer.domainWishList.dto.out.WishListResponseDto;
import starbucks3355.starbucksServer.domainWishList.entity.WishList;
import starbucks3355.starbucksServer.domainWishList.repository.WishListRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {
	private final WishListRepository wishListRepository;

	private final ProductDetailsRepository productDetailsRepository;

	private final DiscountRepository discountRepository;

	@Override
	public List<WishListResponseDto> getMyWishListItems(String memberUuid) {
		List<WishList> myWishList = wishListRepository.findByMemberUuid(memberUuid);

		if (myWishList != null) {
			return myWishList.stream()
				.sorted(Comparator.comparing(WishList::getModDate).reversed())
				.map(myWishListItem -> WishListResponseDto.builder()
					.productUuid(myWishListItem.getProductUuid())
					.memberUuid(myWishListItem.getMemberUuid())
					.isChecked(
						myWishListItem.isChecked()) // Java의 Bean 규약에 따르면, boolean 타입 필드는 is 접두사를 사용하여 getter 메서드가 생성됨
					.limitQuantity(myWishListItem.getLimitQuantity())
					.currentQuantity(myWishListItem.getCurrentQuantity())
					.regDate(myWishListItem.getRegDate())
					.modDate(myWishListItem.getModDate())
					.build()).toList();
		}

		return List.of();
	}

	@Override
	public void addWishList(WishListRequestDto wishListRequestDto) {
		wishListRepository.save(
			wishListRequestDto.toEntity(wishListRequestDto.getProductUuid(), wishListRequestDto.getMemberUuid()));
	}

	@Override
	@Transactional
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
		List<WishList> wishLists = wishListRepository.findByMemberUuid(memberUuid);

		wishLists.stream()
			.filter(WishList::isChecked)
			.forEach(
				wishList -> wishListRepository.deleteByMemberUuidAndProductUuid(memberUuid, wishList.getProductUuid()));

	}

	@Override
	@Transactional
	public void modifyAddWishList(String memberUuid, String productUuid) {
		wishListRepository.findByMemberUuidAndProductUuid(memberUuid, productUuid)
			.ifPresent(wishList -> {
				if (wishList.getCurrentQuantity() < wishList.getLimitQuantity()) {
					wishList.updateCurrentQuantity(wishList.getCurrentQuantity() + 1);
					wishListRepository.save(wishList);
				} else {
					throw new RuntimeException("상품의 최대 수량을 초과할 수 없습니다.");
				}
			});
	}

	@Override
	@Transactional
	public void modifySubtractWishList(String memberUuid, String productUuid) {
		wishListRepository.findByMemberUuidAndProductUuid(memberUuid, productUuid)
			.ifPresent(wishList -> {
				if (wishList.getCurrentQuantity() > 1) {
					wishList.updateCurrentQuantity(wishList.getCurrentQuantity() - 1);
					wishListRepository.save(wishList);
				} else {
					throw new RuntimeException("상품의 최소 수량은 1개입니다.");
				}
			});
	}

	@Override
	@Transactional
	public void modifyWishListCheck(String memberUuid, String productUuid) {
		Optional<WishList> result = wishListRepository.findByMemberUuidAndProductUuid(memberUuid, productUuid);

		WishList wishList = result.get();

		wishList.updateChecked(!wishList.isChecked());

	}

	@Override
	@Transactional
	public void modifyWishListAllSelect(String memberUuid) {
		List<WishList> wishLists = wishListRepository.findByMemberUuid(memberUuid);

		// wishLists에 대해 isChecked의 값이 false 인게 하나라도 존재한다면 모든 wishList의 isChecked를 true로 변경
		if (wishLists.stream().anyMatch(wishList -> wishList.isChecked() == false)) {
			wishLists.forEach(wishList -> {
				wishList.updateChecked(true);
			});
		} else {
			wishLists.forEach(wishList -> {
				wishList.updateChecked(false);
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
	public void addWishListAtProductPage(WishListRequestDto wishListRequestDto, int quantity) {

		List<WishList> wishLists = wishListRepository.findByMemberUuid(wishListRequestDto.getMemberUuid());

		// memberUuid에 대해 productUuid는 최대 20개까지 추가 가능
		if (wishLists.size() >= 20) {
			throw new RuntimeException("하나의 memberUuid에 대해 최대 20개까지 상품을 추가할 수 있습니다.");
		}

		Optional<WishList> existingWishList = wishListRepository.findByMemberUuidAndProductUuid(
			wishListRequestDto.getMemberUuid(),
			wishListRequestDto.getProductUuid());

		if (existingWishList.isPresent()) {
			WishList wishList = existingWishList.get();
			if (wishList.getCurrentQuantity() + quantity <= wishList.getLimitQuantity()) {
				wishList.updateCurrentQuantity(wishList.getCurrentQuantity() + quantity);
				wishListRepository.save(wishList);
			} else {
				throw new RuntimeException("상품의 최대 수량을 초과할 수 없습니다.");
			}
		} else {
			wishListRequestDto.updateCurrentQuantity(quantity);
			wishListRepository.save(
				wishListRequestDto.toEntity(wishListRequestDto.getProductUuid(), wishListRequestDto.getMemberUuid()));
		}
	}

	@Override
	public TotalInfoResponseDto getWishListTotalPriceAndDiscount(String memberUuid) {
		// 회원의 장바구니 레포지토리에서 is_checked가 true인 상품들을 가져와서 다른 레포지토리에 그 상품에 대한 가격과 할인값을 가져와서 총 계산
		List<WishList> wishLists = wishListRepository.findByMemberUuid(memberUuid);

		int totalPrice = 0;
		int totalDiscount = 0;

		for (WishList wishList : wishLists) {
			if (wishList.isChecked() == true) {
				ProductDetails details = productDetailsRepository.findByProductUuid(wishList.getProductUuid())
					.orElseThrow(
						() -> new RuntimeException("Product not found for UUID: " + wishList.getProductUuid()));

				int productPrice = details.getProductPrice();

				log.info("productPriceInfo: {}, {}개", productPrice, wishList.getCurrentQuantity());
				totalPrice += productPrice * wishList.getCurrentQuantity();

				if (discountRepository.findByProductUuid(
					wishList.getProductUuid()).isPresent()) {
					ProductDefaultDisCount productDefaultDisCount = discountRepository.findByProductUuid(
						wishList.getProductUuid()).get();

					int productDiscount = productDefaultDisCount.getDiscountValue();
					log.info("할인률: {}", productDiscount);
					log.info("할인금액: {}", (productPrice * (productDiscount * 0.01)));
					totalDiscount += (productPrice * (productDiscount * 0.01)) * wishList.getCurrentQuantity();
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

