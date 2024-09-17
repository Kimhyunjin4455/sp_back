package starbucks3355.starbucksServer.shipping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingAddRequestDto;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingMemberRequestDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingAllResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingBaseResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingListResponseDto;
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

		int shippingAddressCount = shippingRepository.countByUuid(memberUuid);

		if (shippingAddressCount >= 5) {
			throw new IllegalArgumentException("배송지는 최대 5개까지 등록 가능합니다.");
		}

		if (shippingRepository.existsByDetailAddressAndUuid(shippingAddRequestDto.getDetailAddress(), memberUuid)) {
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

	// // 기본 배송지 변경(수정)
	// @Override
	// @Transactional
	// public void modifyBaseAddress(String uuid, Long deliveryId) {
	//
	// 	// 현재 사용자의 기본 배송지를 찾음
	// 	Optional<ShippingMemberAddress> existingBaseAddress = shippingMemberRepository
	// 		.findFirstByUuidAndBaseAddressTrue(
	// 			uuid);
	//
	// 	// 배송지에서 기본 배송지로 선택할 id 찾기
	// 	ShippingAddress shippingAddress = shippingRepository.findById(deliveryId)
	// 		.orElseThrow(() -> new IllegalArgumentException("해당 배송지가 없습니다."));
	//
	// 	// 기존 배송지가 존재하면 기송 배송지 해제
	// 	existingBaseAddress.ifPresent(address -> {
	// 		ShippingMemberAddress updateMemberAddress = ShippingMemberAddress.builder()
	// 			.address(address.getAddress())
	// 			.detailAddress(address.getDetailAddress())
	// 			.nickname(address.getNickname())
	// 			.phone1(address.getPhone1())
	// 			.phone2(address.getPhone2())
	// 			.receiver(address.getReceiver())
	// 			.postNumber(address.getPostNumber())
	// 			.uuid(address.getUuid())
	// 			.baseAddress(false)
	// 			.build();
	//
	// 		shippingMemberRepository.save(updateMemberAddress);
	// 	});
	//
	// 	ShippingMemberAddress updateNewMem = ShippingMemberAddress.builder()
	// 		.memberAddressId(shippingAddress.getDeliveryId())
	// 		.address(shippingAddress.getAddress())
	// 		.detailAddress(shippingAddress.getDetailAddress())
	// 		.nickname(shippingAddress.getNickname())
	// 		.phone1(shippingAddress.getPhone1())
	// 		.phone2(shippingAddress.getPhone2())
	// 		.receiver(shippingAddress.getReceiver())
	// 		.postNumber(shippingAddress.getPostNumber())
	// 		.uuid(shippingAddress.getUuid())
	// 		.baseAddress(true)
	// 		.build();
	//
	// 	log.info("updateNewMem: {}", updateNewMem);
	// 	shippingMemberRepository.save(updateNewMem);
	//
	// }

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

	//배송지 목록 조회
	@Override
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

		// ShippingMemberAddress shippingMemberAddress = shippingMemberRepository.findFirstByUuidAndBaseAddressTrue(uuid)
		// 	.orElseThrow(() -> new IllegalArgumentException("기본 배송지가 없습니다. "));

		ShippingAddress shippingAddress = shippingRepository.findBaseDeliveryByUuid(uuid)
			.orElseThrow(() -> new IllegalArgumentException("기본 배송지가 없습니다. "));

		return ShippingBaseResponseDto.builder()
			.deliveryId(shippingAddress.getDeliveryId())
			.address(shippingAddress.getAddress())
			.detailAddress(shippingAddress.getDetailAddress())
			.build();

	}

	// @Override
	// @Transactional
	// public void modifyBaseAddress(Long memberId, Long deliveryId) {
	// 	// 1. 회원의 모든 배송지를 조회 (모든 deliveryId 값을 조회)
	// 	List<ShippingAddress> allAddresses = shippingRepository.findAllByMemberId(memberId);
	//
	// 	// 2. 모든 기존 배송지의 baseAddress 값을 false로 설정
	// 	allAddresses.forEach(address -> {
	// 		ShippingAddress updatedAddress = ShippingAddress.builder()
	// 			.deliveryId(address.getDeliveryId()) // 각 배송지의 deliveryId 유지
	// 			.nickname(address.getNickname())
	// 			.address(address.getAddress())
	// 			.detailAddress(address.getDetailAddress())
	// 			.phone1(address.getPhone1())
	// 			.phone2(address.getPhone2())
	// 			.receiver(address.getReceiver())
	// 			.postNumber(address.getPostNumber())
	// 			.uuid(address.getUuid())
	// 			.baseAddress(false) // 기본 배송지 해제
	// 			.build();
	//
	// 		shippingRepository.save(updatedAddress); // 기존 배송지 업데이트
	// 	});
	//
	// 	// 3. 선택한 배송지(deliveryId)를 찾아 baseAddress를 true로 설정
	// 	ShippingAddress shippingAddress = shippingRepository.findById(deliveryId)
	// 		.orElseThrow(() -> new IllegalArgumentException("해당 배송지가 없습니다."));
	//
	// 	ShippingAddress updateNewMem = ShippingAddress.builder()
	// 		.deliveryId(shippingAddress.getDeliveryId()) // 선택된 deliveryId 값 유지
	// 		.nickname(shippingAddress.getNickname())
	// 		.address(shippingAddress.getAddress())
	// 		.detailAddress(shippingAddress.getDetailAddress())
	// 		.phone1(shippingAddress.getPhone1())
	// 		.phone2(shippingAddress.getPhone2())
	// 		.receiver(shippingAddress.getReceiver())
	// 		.postNumber(shippingAddress.getPostNumber())
	// 		.uuid(shippingAddress.getUuid())
	// 		.baseAddress(true) // 새로운 기본 배송지 설정
	// 		.build();
	//
	// 	shippingRepository.save(updateNewMem); // 새로운 기본 배송지로 설정
	// }

	@Override
	@Transactional
	public void modifyBaseAddress(String uuid, Long deliveryId) {

		// 현재 사용자의 기본 배송지를 찾음
		// 옵셔널이라서 널 값이 들어올 수 있음
		Optional<ShippingMemberAddress> existingMemberAddress = shippingMemberRepository.findFirstByUuidAndBaseAddressTrue(
			uuid);

		// 현재 기본 배송지로 돼 있는 값 false로 변경
		// 옵셔널이라서 널 값이 들어올 수 있음
		Optional<ShippingAddress> existingAddress = shippingRepository.findBaseDeliveryByUuidWithJoin(uuid);

		// 기본 배송지 테이블에서 기본 배송지로 설정된 값이  false로 변경
		existingAddress.ifPresent(address -> {
			ShippingAddress updateAddress = ShippingAddress.builder()
				.deliveryId(address.getDeliveryId())
				.address(address.getAddress())
				.detailAddress(address.getDetailAddress())
				.nickname(address.getNickname())
				.postNumber(address.getPostNumber())
				.message(address.getMessage())
				.receiver(address.getReceiver())
				.phone1(address.getPhone1())
				.phone2(address.getPhone2())
				.uuid(address.getUuid())
				.baseAddress(false)
				.build();

			shippingRepository.save(updateAddress);
			// 즉시 DB에 반영
			shippingRepository.flush();
		});

		// 현재 회원의 주소에서 baseAddress 값 false 로 변경
		existingMemberAddress.ifPresent(address -> {
			ShippingMemberAddress updateMemberAddress = ShippingMemberAddress.builder()
				.memberAddressId(address.getMemberAddressId()) // memberId값 유지
				.address(address.getAddress())
				.detailAddress(address.getDetailAddress())
				.nickname(address.getNickname())
				.phone1(address.getPhone1())
				.phone2(address.getPhone2())
				.receiver(address.getReceiver())
				.postNumber(address.getPostNumber())
				.uuid(address.getUuid())
				.baseAddress(false) // 기본 주소 해제
				.build();

			shippingMemberRepository.save(updateMemberAddress);
		});

		// 변경할 deliveryId 값으로 해당 주소 찾기
		ShippingAddress shippingAddress = shippingRepository.findById(deliveryId)
			.orElseThrow(() -> new IllegalArgumentException("해당 배송지가 없습니다."));

		// 새로운 기본 배송지 값
		ShippingAddress newBaseAddress = ShippingAddress.builder()
			.deliveryId(shippingAddress.getDeliveryId())
			.address(shippingAddress.getAddress())
			.detailAddress(shippingAddress.getDetailAddress())
			.postNumber(shippingAddress.getPostNumber())
			.nickname(shippingAddress.getNickname())
			.receiver(shippingAddress.getReceiver())
			.message(shippingAddress.getMessage())
			.phone1(shippingAddress.getPhone1())
			.phone2(shippingAddress.getPhone2())
			.uuid(shippingAddress.getUuid())
			.baseAddress(true)
			.build();

		shippingRepository.save(newBaseAddress);
		// 즉시 DB에 반영
		shippingRepository.flush();

	}
}
