package starbucks3355.starbucksServer.domainWishList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainWishList.dto.in.WishListRequestDto;
import starbucks3355.starbucksServer.domainWishList.dto.out.WishListResponseDto;
import starbucks3355.starbucksServer.domainWishList.entity.WishList;
import starbucks3355.starbucksServer.domainWishList.repository.WishListRepository;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {
	private final WishListRepository wishListRepository;

	@Override
	public List<WishListResponseDto> getMyWishListItems(String memberUuid) {
		List<WishList> myWishList = wishListRepository.findByMemberUuid(memberUuid);

		if (myWishList != null) {
			return myWishList.stream()
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
	public void deleteWishListChecked(String memberUuid) {

	}

	@Override
	public void updateWishList(String userId, String productUuid, int quantity) {
		
	}

	@Override
	public void updateWishListCheck(String userId, String productUuid, boolean isChecked) {

	}

	@Override
	public void updateWishListAllCheck(String userId, boolean isChecked) {

	}

	@Override
	public void updateWishListAllUnCheck(String userId) {

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

}

