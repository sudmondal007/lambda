//package com.pupu.sha.config;
//
//import java.util.function.Consumer;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cglib.core.internal.Function;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.amazonaws.services.lambda.runtime.events.S3Event;
//import com.pupu.sha.service.SpringBootLambdaService;
//@Configuration
//public class FunctionConfig {
//	private static Logger logger = LoggerFactory.getLogger(FunctionConfig.class);
//	
//	@Autowired
//	private SpringBootLambdaService lambdaService;
//	
//	@Bean
//	public Consumer<S3Event> processS3Event() {
//		return event -> lambdaService.handleEvent(event);
//	}
//	
//}


