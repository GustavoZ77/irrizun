package zuun.tech.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zuun.tech.budget.repository.UserRepository;

import java.util.Collections;

@SpringBootApplication
public class BudgetApplication  /*implements CommandLineRunner */{

	@Autowired
	private  UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BudgetApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "9090"));
		app.run(args);

	}

}
