package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.unicon.cas.client.configuration.EnableCasClient;

@SpringBootApplication
@EnableCasClient // 开启cas
public class SpringBootClientApplication2 {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootClientApplication2.class, args);
	}
}
