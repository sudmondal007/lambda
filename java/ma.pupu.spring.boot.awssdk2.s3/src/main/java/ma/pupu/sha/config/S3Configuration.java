//package ma.pupu.sha.config;
//
//import java.net.URI;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//
//@Configuration
//public class S3Configuration {
//	private static Logger logger = LoggerFactory.getLogger(S3Configuration.class);
//	
//	@Autowired
//	private Environment environment;
//	
//	@Bean(name = "s3client")
//	public S3Client getS3Client() {
//		
//		//get region from environment properties
//		String aws_region = environment.getProperty("aws_region");
//		
//		//build region
//		Region region = Region.of(aws_region);
//		
//		// create S3 client
//		S3Client s3Client = S3Client.builder()
//    			.region(Region.of(aws_region))
//    			.endpointOverride(URI.create("https://s3." + region + ".amazonaws.com"))
//    			.forcePathStyle(true)
//    			.build();
//		
//		return s3Client;
//	}
//	
//}
