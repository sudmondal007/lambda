package ma.pupu.sha.sqs.handler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class SQSEventHandler extends SpringBootRequestHandler<SQSEvent, Void> {
	//no additional logic needed
}
