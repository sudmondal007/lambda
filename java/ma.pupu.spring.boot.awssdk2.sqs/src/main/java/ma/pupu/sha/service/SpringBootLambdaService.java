package ma.pupu.sha.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.MessageAttribute;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;

@Service
public class SpringBootLambdaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootLambdaService.class);
	
	public void handleEvent(SQSEvent event) {
		
		for (SQSMessage message : event.getRecords()) {
            String body = message.getBody();
            
            Map<String, MessageAttribute> msgAttributes = message.getMessageAttributes();
            
            if(msgAttributes != null && msgAttributes.size() > 0) {
            	System.out.println("messageBody: " + body + "; msgAttributes: ");
            }
        }
	}
	
}
