package w18d3proveMattina;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import w18d3proveMattina.users.UserRequestPayload;
import w18d3proveMattina.users.UsersService;

@Component
public class UsersRunner implements CommandLineRunner {

	@Autowired
	UsersService us;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 30; i++) {
			String name = faker.name().firstName();
			String surname = faker.name().lastName();
			String email = faker.internet().emailAddress();
			UserRequestPayload rndUser = new UserRequestPayload(name, surname, email);
//			us.create(rndUser);
		}

	}

}
