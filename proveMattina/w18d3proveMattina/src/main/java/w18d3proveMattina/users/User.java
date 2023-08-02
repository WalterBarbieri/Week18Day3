package w18d3proveMattina.users;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	private String surname;
	@Column(unique = true)
	private String email;

	public User(String name, String surname, String email) {
		this.setName(name);
		this.setSurname(surname);
		this.setEmail(email);
	}
}
