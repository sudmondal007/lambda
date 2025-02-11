package ma.pupu.sha.s3.handler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.amazonaws.services.lambda.runtime.events.S3Event;

public class S3EventHandler extends SpringBootRequestHandler<S3Event, Void> {
	//no additional logic needed
}
