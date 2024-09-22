package starbucks3355.starbucksServer.common.S3.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.config.S3Config;

@Service
@RequiredArgsConstructor
@Slf4j
public class AwsFileServiceImpl implements FileService {
	private final AmazonS3Client amazonS3Client;

	private static final String PROFILE_IMG_DIR = "profile/";
	private static final String MEMBER_IMG_DIR = "member/";
	private final S3Config s3Config;

	@Override
	public String savePhoto(MultipartFile multipartFile, String uuid) throws IOException {
		File uploadFile = convert(multipartFile)
			.orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
		return upload(uploadFile, MEMBER_IMG_DIR, uuid);
	}

	@Override
	public String saveProfileImg(MultipartFile multipartFile, String uuid) throws IOException {
		File uploadFile = convert(multipartFile)
			.orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
		return upload(uploadFile, PROFILE_IMG_DIR, uuid);
	}

	@Override
	public void createDir(String bucketName, String folderName) {
		amazonS3Client.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]),
			new ObjectMetadata());
	}

	// S3로 파일 업로드하기
	private String upload(File uploadFile, String dirName, String uuid) {
		String fileName = dirName + uuid + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
		String uploadImageUrl = putS3(uploadFile, fileName); // S3로 업로드
		removeNewFile(uploadFile);
		return uploadImageUrl;
	}

	// S3로 업로드
	private String putS3(File uploadFile, String fileName) {
		amazonS3Client.putObject(new PutObjectRequest(s3Config.getBucket(), fileName, uploadFile)
			.withCannedAcl(CannedAccessControlList.PublicRead));
		return amazonS3Client.getUrl(s3Config.getBucket(), fileName).toString();
	}

	// 로컬에 저장된 이미지 지우기
	private void removeNewFile(File targetFile) {
		if (targetFile.delete()) {
			log.info("File delete success");
		} else {
			log.info("File delete fail");
		}
	}

	// 로컬에 파일 업로드 하기
	private Optional<File> convert(MultipartFile file) throws IOException {
		File convertFile = new File(System.getProperty("user.home") + "/" + file.getOriginalFilename());
		if (convertFile.createNewFile()) { // 지정한 경로에 파일 생성
			try (FileOutputStream fos = new FileOutputStream(convertFile)) {
				fos.write(file.getBytes());
			}
			return Optional.of(convertFile);
		}
		return Optional.empty();
	}
}
