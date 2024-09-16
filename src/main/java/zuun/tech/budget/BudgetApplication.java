package zuun.tech.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zuun.tech.budget.repository.UserRepository;

@SpringBootApplication
public class BudgetApplication  /*implements CommandLineRunner */{

	@Autowired
	private  UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BudgetApplication.class, args);

	}

}
