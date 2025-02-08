package ma.pupu.sha.service;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ma.pupu.sha.utils.AwsConstants;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class SpringBootLambdaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootLambdaService.class);

	private S3Client s3Client = S3Client.builder().region(Region.of(AwsConstants.AWS_REGION)).build();

	public void handleEvent(Map<String, String> event) {

		processS3FileData(event);
	}

	private void processS3FileData(Map<String, String> event) {
		try {
			String content = "Sample Content hello";

			byte[] contentBytes = content.getBytes();

			ByteArrayOutputStream boutputStream = new ByteArrayOutputStream();
			boutputStream.write(contentBytes);

			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
					.contentLength(Long.valueOf(contentBytes.length))
					.bucket(AwsConstants.DEST_BUCKET_NAME)
					.key("testFile1.txt")
					.build();
			
			
			s3Client.putObject(putObjectRequest, RequestBody.fromBytes(boutputStream.toByteArray()));
			
			boutputStream.reset();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
