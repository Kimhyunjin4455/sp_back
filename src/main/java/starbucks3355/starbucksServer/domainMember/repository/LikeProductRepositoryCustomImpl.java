package starbucks3355.starbucksServer.domainMember.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainMember.entity.Likes;
import starbucks3355.starbucksServer.domainMember.entity.QLikes;

@RequiredArgsConstructor
@Repository
public class LikeProductRepositoryCustomImpl implements LikeProductRepositoryCustom {
	private static final int DEFAULT_PAGE_SIZE = 20;
	private static final int DEFAULT_PAGE_NUMBER = 0;

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public CursorPage<String> getLikesList(
		String userUuid,
		Long lastId,
		Integer pageSize,
		Integer page) {

		// 찜한 상품 목록 조회
		QLikes likes = QLikes.likes;
		BooleanBuilder builder = new BooleanBuilder();

		builder.and(likes.uuid.eq(userUuid));

		// 마지막 ID 커서 적용
		Optional.ofNullable(lastId)
			.ifPresent(id -> builder.and(likes.id.lt(id)));

		// 페이지와 페이지 크기 기본값 설정
		int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
		int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

		// offset 계산
		int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

		// 데이터 페칭 (pageSize + 1로 가져와서 다음 페이지 확인)
		List<Likes> content = jpaQueryFactory
			.selectFrom(likes)
			.where(builder)
			.orderBy(likes.id.desc())
			.offset(offset)
			.limit(currentPageSize + 1)
			.fetch();

		// 다음 페이지의 커서 처리 및 hasNext 여부 판단
		Long nextCursor = null;
		boolean hasNext = false;

		if (content.size() > currentPageSize) {
			hasNext = true;
			content = content.subList(0, currentPageSize);
			nextCursor = content.get(content.size() - 1).getId();
		}
		// productCode 리스트로 변환
		List<String> productUuid = content.stream()
			.map(Likes::getProductUuid) // Likes 엔티티에서 회원 Uuid를 가져오는 메서드
			.toList();

		// CursorPage 객체 반환
		return new CursorPage<>(productUuid, nextCursor, hasNext, currentPageSize, currentPage);
	}

}
