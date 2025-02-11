package ma.pupu.sha.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;

@Service
public class SpringBootLambdaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootLambdaService.class);
	
	public void handleEvent(S3Event event) {
		processS3FileData(event);
	}
	
	private void processS3FileData(S3Event event) {
		for (S3EventNotificationRecord record : event.getRecords()) {
            String bucketName = record.getS3().getBucket().getName();
            String objectKey = record.getS3().getObject().getKey();
            
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(System.getenv("aws_region")).build();
            AmazonSQS sqsClient = AmazonSQSAsyncClient.builder().withRegion(System.getenv("aws_region")).build();
            
            try {
            	
            	S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, objectKey));
                BufferedReader reader = new BufferedReader(new InputStreamReader(s3Object.getObjectContent()));
                
                String line;

                while ((line = reader.readLine()) != null) {
                    // Process each line of the file
                	LOGGER.info(line);
                	System.out.println(line);
                	sendMessageToQueue(line, sqsClient);
                }

            } catch (IOException e) {
            	LOGGER.info("Error processing file: " + e.getMessage());
            }
        }
	}
	
	
	private void sendMessageToQueue(String line, AmazonSQS sqsClient) {
		try {
			String sqsUrl = System.getenv("sqsUrl");
			String sqsMessageBody = System.getenv("sqsMessageBody");
			String sqsGrpId = System.getenv("sqsGrpId");

			 Map<String, MessageAttributeValue> sqsMsgAttributes = new HashMap<>();

			sqsMsgAttributes.put("attr1", new MessageAttributeValue().withDataType("String").withStringValue("attr1value"));

            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(sqsUrl)
                .withMessageBody(sqsMessageBody)
                .withMessageGroupId(sqsGrpId)
                .withMessageDeduplicationId(UUID.randomUUID().toString())
                .withMessageAttributes(sqsMsgAttributes);

            sqsClient.sendMessage(sendMessageRequest); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
