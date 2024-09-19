package starbucks3355.starbucksServer.shipping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.exception.BaseException;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingAddRequestDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingListResponseDto;
import starbucks3355.starbucksServer.shipping.entity.ShippingAddress;
import starbucks3355.starbucksServer.shipping.repository.ShippingRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingServiceImpl implements ShippingService {

	private final ShippingRepository shippingRepository;

	// 배송지 추가
	@Override
	public void createShipping(String memberUuid, ShippingAddRequestDto shippingAddRequestDto) {

		int shippingAddressCount = shippingRepository.countByUuid(memberUuid);

		if (shippingAddressCount >= 5) {
			throw new BaseException(BaseResponseStatus.COUNT_OVER);  // 배송지 최대 개수 초과 예외
		}

		if (shippingRepository.existsByDetailAddressAndUuid(shippingAddRequestDto.getDetailAddress(), memberUuid)) {
			throw new BaseException(BaseResponseStatus.DUPLICATE_ADDRESS);  // 중복된 주소 예외
		}
		if (shippingRepository.findBaseAddressByUuid(memberUuid).isPresent()) {
			throw new BaseException(BaseResponseStatus.BASE_ADDRESS_EXIST);  // 기본 배송지가 이미 존재하는 경우
		}

		// ShippingAddress 엔티티 생성
		ShippingAddress shippingAddress = shippingAddRequestDto.toEntity(memberUuid, shippingAddRequestDto);

		// 예외 처리 없이 엔티티 저장
		shippingRepository.save(shippingAddress);
	}

	//배송지 목록 조회
	@Override
	@Transactional(readOnly = true)
	public List<ShippingListResponseDto> getShippingList(String uuid) {
		List<ShippingAddress> shippingAddressList = shippingRepository.findByUuid(uuid);
		return shippingAddressList.stream()
			.map(
				shippingAddress -> ShippingListResponseDto.builder()
					.deliveryId(shippingAddress.getDeliveryId())
					.receiver(shippingAddress.getReceiver())
					.phone1(shippingAddress.getPhone1())
					.address(shippingAddress.getAddress())
					.detailAddress(shippingAddress.getDetailAddress())
					.build())
			.toList();
	}

	@Override
	public void deleteShipping(String uuid, Long deliveryId) {
		ShippingAddress shippingAddress = shippingRepository.findById(deliveryId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.SHIPPING_ADDRESS_NOT_FOUND));

		shippingRepository.deleteById(shippingAddress.getDeliveryId());
	}

	// // 모든 배송지 조회
	// @Override
	// @Transactional(readOnly = true)
	// public List<ShippingAllResponseDto> getAllShippingAddress() {
	// 	return shippingRepository.findAll(
	// 			Sort.by(Sort.Direction.ASC, "deliveryId"))
	// 		.stream()
	// 		.map(
	// 			shippingAddress -> ShippingAllResponseDto.builder()
	// 				.deliveryId(shippingAddress.getDeliveryId())
	// 				.nickname(shippingAddress.getNickname())
	// 				.address(shippingAddress.getAddress())
	// 				.detailAddress(shippingAddress.getDetailAddress())
	// 				.build())
	// 		.toList();
	//
	// }

	@Override
	@Transactional
	public void modifyBaseAddress(String uuid, Long deliveryId) {

		// 현재 기본 배송지로 돼 있는 값 false로 변경
		// 옵셔널이라서 널 값이 들어올 수 있음
		Optional<ShippingAddress> existingAddress = shippingRepository.findBaseAddressByUuid(uuid);

		// 1. 기존 기본 배송지를 찾아서 기본 배송지 해제
		// 2. 빌더를 통해 업데이트
		// 3. 저장
		existingAddress.ifPresent(shippingAddress -> {
			ShippingAddress updateAddress = ShippingAddress.builder()
				.deliveryId(shippingAddress.getDeliveryId())
				.address(shippingAddress.getAddress())
				.detailAddress(shippingAddress.getDetailAddress())
				.phone1(shippingAddress.getPhone1())
				.phone2(shippingAddress.getPhone2())
				.receiver(shippingAddress.getReceiver())
				.message(shippingAddress.getMessage())
				.nickname(shippingAddress.getNickname())
				.postNumber(shippingAddress.getPostNumber())
				.uuid(shippingAddress.getUuid())
				.baseAddress(false)
				.build();
			shippingRepository.save(updateAddress);
		});

		// 새로운 기본 배송지로 설정
		ShippingAddress newBaseAddress = shippingRepository.findById(deliveryId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.SHIPPING_ID_NOT_EXIST));

		shippingRepository.save(ShippingAddress.builder()
			.deliveryId(newBaseAddress.getDeliveryId())
			.address(newBaseAddress.getAddress())
			.detailAddress(newBaseAddress.getDetailAddress())
			.phone1(newBaseAddress.getPhone1())
			.phone2(newBaseAddress.getPhone2())
			.receiver(newBaseAddress.getReceiver())
			.message(newBaseAddress.getMessage())
			.nickname(newBaseAddress.getNickname())
			.postNumber(newBaseAddress.getPostNumber())
			.uuid(newBaseAddress.getUuid())
			.baseAddress(true)
			.build());
	}
}
