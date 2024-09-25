package starbucks3355.starbucksServer.common.S3.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.config.S3Config;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.exception.BaseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AwsFileServiceImpl implements FileService {
	private final AmazonS3Client amazonS3Client;

	private static final String PROFILE_IMG_DIR = "profile/";
	private static final String IMAGE_IMG_DIR = "image/";
	private final S3Config s3Config;
	// @Value("${s3Path}")
	// private String s3Path;
	private final AmazonS3 amazonS3;

	@Override
	public String saveMedia(MultipartFile multipartFile, String uuid, String fileName) {

		// String fileName = createFileName(multipartFile.getOriginalFilename());
		log.info("fileName: {}", fileName);
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(multipartFile.getSize());
		objectMetadata.setContentType(multipartFile.getContentType());

		log.info("saveImage");

		try (InputStream inputStream = multipartFile.getInputStream()) {
			log.info("미디어 등록 - 등록전");
			amazonS3.putObject(new PutObjectRequest(s3Config.getBucket(), fileName, inputStream, objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
			log.info("미디어 등록 - 등록후");
		} catch (IOException e) {
			log.error("S3에 파일 저장 중 오류 발생: {}", e.getMessage());
			throw new BaseException(BaseResponseStatus.SAVE_MEDIA_FAILED);
		}

		return amazonS3.getUrl(s3Config.getBucket(), fileName).toString();

		// try {
		// 	File uploadFile = convert(multipartFile).get();
		// 	return upload(uploadFile, MEMBER_IMG_DIR, uuid);
		//
		// } catch (Exception e) {
		// 	log.info("error: MultipartFile ->{}", e);
		// }
		// // 		.orElseThrow(() ->
		// // 		{
		// // 			return new IllegalArgumentException("error: MultipartFile -> File convert fail")
		// // }
		// // 		);
		// return null;
	}

	@Override
	public void deleteMedia(String fileName) {
		try {
			log.info("미디어 삭제 - 삭제전");
			amazonS3.deleteObject(new DeleteObjectRequest(s3Config.getBucket(), fileName));
			log.info("미디어 삭제 - 삭제후");
		} catch (Exception e) {
			log.error("S3에서 파일 삭제 중 오류 발생: {}", e.getMessage());
			throw new BaseException(BaseResponseStatus.DELETE_MEDIA_FAILED);
		}
	}

	// 파일명 난수화 (이전에 올린 이미지 또 올릴수도)
	private String createFileName(String filename) {
		return UUID.randomUUID().toString().concat(getFileExtension(filename));
	}

	// 파일 형식 .뒤에 삭제
	private String getFileExtension(String fileName) {
		try {
			return fileName.substring(fileName.lastIndexOf("."));
		} catch (StringIndexOutOfBoundsException e) {
			throw new BaseException(BaseResponseStatus.WRONG_FILE_TYPE);
		}
	}

	// @Override
	// public String saveProfileImg(MultipartFile multipartFile, String uuid) throws IOException {
	// 	File uploadFile = convert(multipartFile)
	// 		.orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
	// 	return upload(uploadFile, PROFILE_IMG_DIR, uuid);
	// }

	@Override
	public void createDir(String bucketName, String folderName) {
		amazonS3Client.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]),
			new ObjectMetadata());
	}

	@Override
	public byte[] getFile(String fileName) {
		S3Object s3Object = amazonS3Client.getObject(s3Config.getBucket(), fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		try {
			return IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			throw new BaseException(BaseResponseStatus.NO_EXIST_S3_IMAGE);
		}
	}

	// S3로 파일 업로드하기
	// private String upload(File uploadFile, String dirName, String uuid) {
	// 	String fileName = dirName + uuid + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
	// 	String uploadImageUrl = putS3(uploadFile, fileName); // S3로 업로드
	// 	removeNewFile(uploadFile);
	// 	return uploadImageUrl;
	// }

	// S3로 업로드
	private String putS3(File uploadFile, String fileName) {

		log.info("putS3");
		amazonS3Client.putObject(new PutObjectRequest(s3Config.getBucket(), fileName, uploadFile)
			.withCannedAcl(CannedAccessControlList.PublicRead));
		log.info("putS3 success");
		return amazonS3Client.getUrl(s3Config.getBucket(), fileName).toString();
	}

	// 로컬에 저장된 이미지 지우기
	// private void removeNewFile(File targetFile) {
	// 	if (targetFile.delete()) {
	// 		log.info("File delete success");
	// 	} else {
	// 		log.info("File delete fail");
	// 	}
	// }
	//
	// // 로컬에 파일 업로드 하기
	// private Optional<File> convert(MultipartFile file) throws IOException {
	// 	log.info("convert");
	// 	Date date = new Date();
	// 	File convertFile = new File(file.getOriginalFilename() + date.toString());
	// 	log.info("convertFile: {}", convertFile);
	// 	if (convertFile.createNewFile()) { // 지정한 경로에 파일 생성
	// 		log.info("File create success");
	// 		try (FileOutputStream fos = new FileOutputStream(convertFile)) {
	// 			fos.write(file.getBytes());
	// 		}
	// 	}
	// 	return Optional.of(convertFile);
	// }
}
