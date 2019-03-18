package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.unicon.cas.client.configuration.EnableCasClient;

@SpringBootApplication
@EnableCasClient // 开启cas
public class SpringBootClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootClientApplication.class, args);
	}
}
