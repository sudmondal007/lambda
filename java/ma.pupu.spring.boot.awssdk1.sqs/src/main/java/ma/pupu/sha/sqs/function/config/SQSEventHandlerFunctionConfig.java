package ma.pupu.sha.sqs.function.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;

import ma.pupu.sha.service.SpringBootLambdaService;

@Configuration
public class SQSEventHandlerFunctionConfig {
	
	@Autowired
	private SpringBootLambdaService service;
	
	@Bean
	public Consumer<SQSEvent> s3EventHandler() {
		return sqsevent -> service.handleEvent(sqsevent);
	}
}
