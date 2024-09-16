package starbucks3355.starbucksServer.shipping.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingAddRequestDto;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingMemberRequestDto;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingPutRequestDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingAllResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingBaseResponseDto;
import starbucks3355.starbucksServer.shipping.entity.ShippingAddress;
import starbucks3355.starbucksServer.shipping.entity.ShippingMemberAddress;
import starbucks3355.starbucksServer.shipping.repository.ShippingMemberRepository;
import starbucks3355.starbucksServer.shipping.repository.ShippingRepository;

@Service
@RequiredArgsConstructor
@Slf4j
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
	public void createShipping(String memberUuid, ShippingAddRequestDto shippingAddRequestDto) {

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

	// 기본 배송지 변경(수정)
	@Override
	public void modifyBaseAddress(ShippingPutRequestDto shippingPutRequestDto, String uuid) {
		// 1. ShippingMemberRepository를 통해 현재 사용자의 기본 배송지를 찾는다.
		ShippingMemberAddress currentMemberAddress = shippingMemberRepository.findByUuidAndBaseAddressTrue(uuid)
			.orElseThrow(() -> new IllegalArgumentException("기존 기본 배송지가 없습니다."));

		// 2. ShippingRepository를 통해 현재 기본 배송지를 찾는다.
		ShippingAddress currentBaseAddress = shippingRepository.findBaseDeliveryByUuid(uuid)
			.orElseThrow(() -> new IllegalArgumentException("기본 배송지가 없습니다."));

		// 3. 기존 기본 배송지의 baseAddress 값을 false로 설정하고 저장한다.
		ShippingAddress updatedOldBaseAddress = ShippingAddress.builder()
			.deliveryId(currentBaseAddress.getDeliveryId())
			.address(currentBaseAddress.getAddress())
			.detailAddress(currentBaseAddress.getDetailAddress())
			.nickname(currentBaseAddress.getNickname())
			.phone1(currentBaseAddress.getPhone1())
			.phone2(currentBaseAddress.getPhone2())
			.uuid(currentBaseAddress.getUuid())
			.postNumber(currentBaseAddress.getPostNumber())
			.message(currentBaseAddress.getMessage())
			.baseAddress(false) // 기본 배송지 해제
			.build();

		shippingRepository.save(updatedOldBaseAddress);

		// 4. 새로운 배송지를 업데이트할 필드들로 빌더를 통해 설정한다.
		ShippingAddress newBaseAddress = ShippingAddress.builder()
			.deliveryId(shippingPutRequestDto.getDeliveryId())
			.address(shippingPutRequestDto.getAddress())
			.detailAddress(shippingPutRequestDto.getDetailAddress())
			.nickname(shippingPutRequestDto.getNickName())
			.phone1(shippingPutRequestDto.getPhone1())
			.phone2(shippingPutRequestDto.getPhone2())
			.uuid(uuid)
			.postNumber(shippingPutRequestDto.getPostNumber()) // 새로운 우편번호
			.baseAddress(true) // 새로운 기본 배송지 설정
			.build();

		// 5. 새로운 기본 배송지를 저장한다.
		shippingRepository.save(newBaseAddress);

		// 6. ShippingMemberRepository에 해당 회원의 기본 주소를 업데이트한다.
		ShippingMemberAddress updatedMemberAddress = shippingPutRequestDto.toEntity(shippingPutRequestDto);
		shippingMemberRepository.save(updatedMemberAddress);
	}

	// 회원의 주소 등록
	@Override
	@Transactional
	public void enrollAddress(String uuid, ShippingMemberRequestDto shippingMemberRequestDto) {
		log.info("UUID: {}", uuid);
		log.info("Detail Address: {}", shippingMemberRequestDto.getDetailAddress().trim().toLowerCase());

		if (shippingMemberRepository.existsByUuid(uuid)) {
			throw new IllegalArgumentException("이미 등록된 주소입니다.");
		}

		ShippingMemberAddress shippingMemberAddress = shippingMemberRequestDto.toEntity(uuid);
		shippingMemberRepository.save(shippingMemberAddress);

		// baseAddress가 true인 경우 ShippingAddress에 저장
		if (shippingMemberRequestDto.isBaseAddress()) {
			ShippingAddress shippingAddress = ShippingAddress.builder()
				.address(shippingMemberRequestDto.getAddress())
				.detailAddress(shippingMemberRequestDto.getDetailAddress())
				.nickname(shippingMemberRequestDto.getNickName())
				.phone1(shippingMemberRequestDto.getPhone1())
				.phone2(shippingMemberRequestDto.getPhone2())
				.uuid(uuid)
				.postNumber(shippingMemberRequestDto.getPostNumber())
				.baseAddress(true)
				.build();

			shippingRepository.save(shippingAddress);
		}
	}

	// 모든 배송지 조회
	@Override
	public List<ShippingAllResponseDto> getAllShippingAddress() {
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
	public ShippingBaseResponseDto getBaseShippingAddress(String uuid) {

		ShippingMemberAddress shippingMemberAddress = shippingMemberRepository.findByUuidAndBaseAddressTrue(uuid)
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
