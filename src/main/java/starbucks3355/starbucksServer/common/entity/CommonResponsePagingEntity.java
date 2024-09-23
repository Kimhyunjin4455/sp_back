package starbucks3355.starbucksServer.common.entity;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponsePagingEntity<T> {
	HttpStatusCode httpStatus;
	Boolean isSuccess;
	String message;
	int code;
	T result;
	private boolean hasNext; // 다음 페이지 여부 추가
}
