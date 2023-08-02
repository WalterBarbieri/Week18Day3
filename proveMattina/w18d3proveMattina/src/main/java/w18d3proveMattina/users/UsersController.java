package w18d3proveMattina.users;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

	private final UsersService us;

	@Autowired
	public UsersController(UsersService us) {
		this.us = us;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody UserRequestPayload body) {
		User userCreated = us.create(body);
		return userCreated;
	}

	@GetMapping
	public List<User> getUsers() {
		return us.find();
	}

	@GetMapping("/{userId}")
	public User findById(@PathVariable UUID userId) {
		return us.findById(userId);
	}

	@PutMapping("/{userId}")
	public User updateUser(@PathVariable UUID userId, @RequestBody UserRequestPayload body) {
		return us.findByIdAndUpdate(userId, body);

	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId) {
		us.findByIdAndDelete(userId);
	}

}
