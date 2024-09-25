package starbucks3355.starbucksServer.common.S3.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String saveMedia(MultipartFile multipartFile, String uuid, String fileName);

	void deleteMedia(String fileName);

	// 멤버 이미지 저장
	//String savePhoto(MultipartFile multipartFile, String uuid) throws IOException;

	// S3에 디렉토리 생성
	void createDir(String bucketName, String folderName);

	public byte[] getFile(String fileName);

}
