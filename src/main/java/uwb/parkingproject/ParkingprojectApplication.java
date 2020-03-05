package uwb.parkingproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"uwb.parkingproject"})
public class ParkingprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingprojectApplication.class, args);
	}

}