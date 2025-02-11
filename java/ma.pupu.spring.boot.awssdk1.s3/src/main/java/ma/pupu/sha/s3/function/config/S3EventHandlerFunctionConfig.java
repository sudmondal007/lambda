package ma.pupu.sha.s3.function.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.lambda.runtime.events.S3Event;

import ma.pupu.sha.service.SpringBootLambdaService;

@Configuration
public class S3EventHandlerFunctionConfig {
	
	@Autowired
	private SpringBootLambdaService service;
	
	@Bean
	public Consumer<S3Event> s3EventHandler() {
		return seEvent -> service.handleEvent(seEvent);
	}
}
