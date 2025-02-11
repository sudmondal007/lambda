package ma.pupu.sha.service;

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

	public void handleEvent(Map<String, String> event) {
		processS3FileData(event);
	}

	private void processS3FileData(Map<String, String> event) {
		try {
			
			String awsRegion = System.getenv("aws_region");
			String bucket = System.getenv("bucket_name");
			String key = "sampleFile.txt";
			
			
			String content = "Sample Content to write in the file";

			byte[] contentBytes = content.getBytes();

			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
					.contentLength(Long.valueOf(contentBytes.length))
					.bucket(bucket)
					.key(key)
					.build();
			
			S3Client s3Client = S3Client.builder().region(Region.of(awsRegion)).build();
			
			s3Client.putObject(putObjectRequest, RequestBody.fromBytes(contentBytes));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
