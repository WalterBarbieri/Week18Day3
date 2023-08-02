package w18d3proveMattina.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class UserRequestPayload {
	private String name;
	private String surname;
	private String email;
}
