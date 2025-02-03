package ma.pupu.sha.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;

import ma.pupu.sha.utils.AwsConstants;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SpringBootLambdaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootLambdaService.class);
	
	private S3Client s3Client = S3Client.builder()
			.region(Region.of(AwsConstants.AWS_REGION))
			.endpointOverride(URI.create(AwsConstants.AWS_S3_URL))
			.forcePathStyle(true)
			.build();
	
	private SqsClient sqsClient = SqsClient.builder()
			.region(Region.of(AwsConstants.AWS_REGION))
			.endpointOverride(URI.create(AwsConstants.AWS_SQS_URL))
			.build();
	
	public void handleEvent(S3Event event) {
		
		processS3FileData(event);
	}
	
	private void processS3FileData(S3Event event) {
		for (S3EventNotificationRecord record : event.getRecords()) {
            String bucketName = record.getS3().getBucket().getName();
            String objectKey = record.getS3().getObject().getKey();
            
            System.out.println("\nbucketName=" + bucketName + "; objectKey=" + objectKey);
            
            try {
            	GetObjectRequest objectRequest = GetObjectRequest.builder().key(objectKey).bucket(bucketName).build();
            	
            	ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(objectRequest);
            			
                BufferedReader reader = new BufferedReader(new InputStreamReader(objectBytes.asInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    // Process each line of the file
                	LOGGER.info(line);
                	System.out.println(line);
                	sendMessageToQueue(line);
                }

            } catch (IOException e) {
            	LOGGER.info("Error processing file: " + e.getMessage());
            }
        }
	}
	
	
	private void sendMessageToQueue(String line) {
		System.out.println("sendMessageToQueue()..................");
		try {
			String sqsUrl = System.getenv("sqsUrl");
			String sqsMessageBody = System.getenv("sqsMessageBody");
			String sqsGrpId = System.getenv("sqsGrpId");

			Map<String, MessageAttributeValue> msgAttrVal = new HashMap<>();

			MessageAttributeValue attributeValue = MessageAttributeValue.builder()
					.dataType("String")
					.stringValue("StringValue1")
					.build();

			msgAttrVal.put("helloKey1", attributeValue);

			String uuid = UUID.randomUUID().toString();

			SendMessageRequest messageRequest = SendMessageRequest.builder()
					.queueUrl(sqsUrl)
					.messageBody(sqsMessageBody)
					.messageGroupId(sqsGrpId)
					.messageDeduplicationId(uuid)
					.messageAttributes(msgAttrVal)
					.build();

			sqsClient.sendMessage(messageRequest);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
