package ro.fortech;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ro.fortech.entities.Door;
import ro.fortech.entities.User;
import ro.fortech.repositories.DoorRepository;
import ro.fortech.repositories.UserRepository;
@SpringBootApplication
@ComponentScan(basePackages = "ro.fortech")
@EntityScan(basePackages = "ro.fortech.entities")
@EnableJpaRepositories(basePackages = "ro.fortech.repositories")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
