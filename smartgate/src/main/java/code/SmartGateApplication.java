package code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartGateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartGateApplication.class, args);
		System.out.println("Smart gate application started successfully."); 
	}

}
