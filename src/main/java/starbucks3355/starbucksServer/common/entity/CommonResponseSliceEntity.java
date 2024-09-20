package starbucks3355.starbucksServer.common.entity;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseSliceEntity<T> {
	private HttpStatus status;
	private String message;
	private T result;
	private boolean hasNext; // 다음 페이지 여부 추가
}
