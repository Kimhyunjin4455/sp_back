package starbucks3355.starbucksServer.shipping.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingAddRequestDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingAllResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingBaseResponseDto;
import starbucks3355.starbucksServer.shipping.entity.ShippingAddress;
import starbucks3355.starbucksServer.shipping.entity.ShippingMemberAddress;
import starbucks3355.starbucksServer.shipping.repository.ShippingMemberRepository;
import starbucks3355.starbucksServer.shipping.repository.ShippingRepository;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {

	private final ShippingRepository shippingRepository;
	private final ShippingMemberRepository shippingMemberRepository;

	// 배송지 추가 생성
	// @Override
	// public void createAddDelivery(DeliveryAddRequestDto deliveryAddRequestDto) {
	//
	// 	if (deliveryRepository.existsByAddress(deliveryAddRequestDto.getAddress())) {
	// 		throw new IllegalArgumentException("이미 등록된 주소입니다.");
	// 	}
	// 	Delivery delivery = deliveryAddRequestDto.toEntity();
	//
	// 	try {
	// 		deliveryRepository.save(delivery);
	// 	} catch (IllegalArgumentException e) {
	// 		throw e;
	// 	} catch (Exception e) {
	// 		throw new IllegalArgumentException("주소 등록에 실패했습니다.");
	// 	}
	// }

	// 배송지 추가
	@Override
	public void createDelivery(String memberUuid, ShippingAddRequestDto shippingAddRequestDto) {

		if (shippingRepository.existsByAddress(shippingAddRequestDto.getAddress())) {
			throw new IllegalArgumentException("이미 등록된 주소입니다.");
		}

		ShippingAddress shippingAddress = shippingAddRequestDto.toEntity(memberUuid, shippingAddRequestDto);

		try {
			shippingRepository.save(shippingAddress);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalArgumentException("주소 등록에 실패했습니다.");
		}
	}

	// 모든 배송지 조회
	@Override
	public List<ShippingAllResponseDto> getAllDelivery() {
		return shippingRepository.findAll(
				Sort.by(Sort.Direction.ASC, "deliveryId"))
			.stream()
			.map(
				shippingAddress -> ShippingAllResponseDto.builder()
					.deliveryId(shippingAddress.getDeliveryId())
					.nickname(shippingAddress.getNickname())
					.address(shippingAddress.getAddress())
					.detailAddress(shippingAddress.getDetailAddress())
					.build())
			.toList();

	}

	// 기본 배송지 조회
	@Override
	public ShippingBaseResponseDto getBaseDelivery(String uuid) {

		ShippingMemberAddress shippingMemberAddress = shippingMemberRepository.findByUuidAndBasicAddressTrue(uuid)
			.orElseThrow(() -> new IllegalArgumentException("기본 배송지가 없습니다. "));

		ShippingAddress shippingAddress = shippingRepository.findBaseDeliveryByUuid(uuid)
			.orElseThrow(() -> new IllegalArgumentException("기본 배송지가 없습니다. "));

		return ShippingBaseResponseDto.builder()
			.deliveryId(shippingAddress.getDeliveryId())
			.address(shippingAddress.getAddress())
			.detailAddress(shippingAddress.getDetailAddress())
			.build();

	}

}
