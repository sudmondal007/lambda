package ma.pupu.sha.service;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class SpringBootLambdaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootLambdaService.class);
	
	public void handleEvent(Map<String, String> map) {
		processS3FileData(map);
	}
	
	private void processS3FileData(Map<String, String> map) {
		
		String awsRegion = System.getenv("aws_region");
		String bucket = System.getenv("bucket_name");
		String key = "sampleFile.txt";
		
		String content = "Sample Content to write in the file";
		
		byte[] contentBytes = content.getBytes();

		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(contentBytes.length);
			ByteArrayInputStream inputStream = new ByteArrayInputStream(contentBytes);
	
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(awsRegion).build();
			
			s3Client.putObject(bucket, key, inputStream, metadata);
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
