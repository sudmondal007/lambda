package ma.pupu.sha.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.lambda.runtime.events.S3Event;

import ma.pupu.sha.service.SpringBootLambdaService;

@Service
public class S3ConsumerService implements Consumer<S3Event> {
	
	@Autowired
	private SpringBootLambdaService lambdaService;

	@Override
	public void accept(S3Event event) {
		lambdaService.handleEvent(event);
	}

}
