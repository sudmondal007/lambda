package ma.pupu.sha.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@Import({ S3Configuration.class, S3ConsumerService.class, SpringBootLambdaService.class})
@ComponentScan(basePackages = { "ma.pupu.sha.*" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
