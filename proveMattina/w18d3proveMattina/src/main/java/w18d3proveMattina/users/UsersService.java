package w18d3proveMattina.users;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w18d3proveMattina.exceptions.NotFoundException;

@Service
public class UsersService {

	private final UsersRepository ur;

	@Autowired
	public UsersService(UsersRepository ur) {
		this.ur = ur;
	}

	public User create(UserRequestPayload body) {
		// TODO: check if email already in use
		User newUser = new User(body.getName(), body.getSurname(), body.getEmail());
		return ur.save(newUser);
	}

	public List<User> find() {
		return ur.findAll();
	}

	public User findById(UUID userId) throws NotFoundException {
		return ur.findById(userId).orElseThrow(() -> new NotFoundException(userId));
	}

	public User findByIdAndUpdate(UUID userId, UserRequestPayload body) throws NotFoundException {
		User found = this.findById(userId);
		found.setEmail(body.getEmail());
		found.setName(body.getName());
		found.setSurname(body.getSurname());

		return ur.save(found);
	}

	public void findByIdAndDelete(UUID userId) throws NotFoundException {
		User found = this.findById(userId);
		ur.delete(found);
	}
}
