package ma.pupu.sha.config;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.pupu.sha.service.SpringBootLambdaService;

@Service
public class EventConsumerService implements Consumer<Map<String, String>> {
	
	@Autowired
	private SpringBootLambdaService lambdaService;

	@Override
	public void accept(Map<String, String> event) {
		lambdaService.handleEvent(event);
	}

}
