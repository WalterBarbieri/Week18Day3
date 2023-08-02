package w18d3esercizio.utenti;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UtenteRequestPayload {
	private String userName;
	private String nomeCompleto;
	private String email;
}
