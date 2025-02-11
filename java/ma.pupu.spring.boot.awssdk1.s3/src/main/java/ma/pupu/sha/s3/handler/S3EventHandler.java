package ma.pupu.sha.s3.handler;

import java.util.Map;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class S3EventHandler extends SpringBootRequestHandler<Map<String, String>, Void> {
	//no additional logic needed
}
