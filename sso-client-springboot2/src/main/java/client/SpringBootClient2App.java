package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.unicon.cas.client.configuration.EnableCasClient;

@SpringBootApplication
@EnableCasClient // 开启cas
public class SpringBootClient2App {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootClient2App.class, args);
	}
}
