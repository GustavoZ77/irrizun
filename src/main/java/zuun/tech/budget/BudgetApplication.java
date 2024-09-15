package zuun.tech.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zuun.tech.budget.domain.User;
import zuun.tech.budget.repository.UserRepository;

@SpringBootApplication
public class BudgetApplication  /*implements CommandLineRunner */{

	@Autowired
	private  UserRepository userRepository;

//	@Override
//	public void run(String... args) throws Exception {
//		User user = User.builder().userName("tkd77").pwd("123").build();
//		userRepository.save(user);
//	}

	public static void main(String[] args) {
		SpringApplication.run(BudgetApplication.class, args);

	}

}
