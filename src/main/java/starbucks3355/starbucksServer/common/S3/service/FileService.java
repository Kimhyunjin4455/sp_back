package starbucks3355.starbucksServer.common.S3.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	// 프로필 이미지 저장
	String saveProfileImg(MultipartFile multipartFile, String uuid) throws IOException;

	// 멤버 이미지 저장
	String savePhoto(MultipartFile multipartFile, String uuid) throws IOException;

	// S3에 디렉토리 생성
	void createDir(String bucketName, String folderName);
}
