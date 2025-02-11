package ma.pupu.sha.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication
//@Import({ S3Configuration.class, S3ConsumerService.class, SpringBootLambdaService.class})
@ComponentScan(basePackages = { "ma.pupu.sha.*" })
//@ImportResource({"classpath:spring-config/spring_ssl_context.xml"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	//@Autowired
	//@Qualifier("truststore")
	//private MethodInvokingFactoryBean bean;
	
//	@PostConstruct
//	public void init() {
//		try {
//			Resource r = loadEmployeesWithClassPathResource();
//			if(r != null)
//				System.out.println("resource found..................." + r.getURI().toString());
//			else
//				System.out.println("resource not found...................");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("resource: " + e.getMessage());
//		}
//		
//		
//		try {
//			String absPath = (getClass().getClassLoader().getResource("var/task/cert/mytestRes.jks").toURI()).toString();
//			System.out.println("absPath: " + absPath);
//		} catch (Exception ex) {
//			System.out.println("absPath: " + ex.getMessage());
//		}
//	}
//	
//	@Autowired
//	ResourceLoader resourceLoader;
//	
//	public Resource loadEmployeesWithClassPathResource() {
//	    //return new ClassPathResource("mytestRes.jks", this.getClass().getClassLoader());
//		return resourceLoader.getResource(
//			      "classpath:var/task/cert/mytestRes.jks");
//	}

}
