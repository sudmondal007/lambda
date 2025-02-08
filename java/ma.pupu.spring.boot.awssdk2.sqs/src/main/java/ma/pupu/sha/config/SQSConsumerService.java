package ma.pupu.sha.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;

import ma.pupu.sha.service.SpringBootLambdaService;

@Service
public class SQSConsumerService implements Consumer<SQSEvent> {
	
	@Autowired
	private SpringBootLambdaService lambdaService;

	@Override
	public void accept(SQSEvent event) {
		lambdaService.handleEvent(event);
	}

}
