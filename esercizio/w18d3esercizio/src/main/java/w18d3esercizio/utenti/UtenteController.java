package w18d3esercizio.utenti;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
	private final UtenteService us;

	@Autowired
	public UtenteController(UtenteService us) {
		this.us = us;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Utente crea(@RequestBody UtenteRequestPayload body) {
		Utente utente = us.creaUtente(body);
		return utente;
	}

	@GetMapping
	public Page<Utente> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "utenteId") String sortBy) {
		return us.getUtenti(page, size, sortBy);

	}

	@GetMapping("/{utenteId}")
	public Utente getUtente(@PathVariable UUID utenteId) {
		return us.getUtenteById(utenteId);
	}

	@PutMapping("/{utenteId}")
	public Utente getUtenteUpdate(@PathVariable UUID utenteId, @RequestBody UtenteRequestPayload body) {
		return us.getUtenteByIdAndUpdate(utenteId, body);
	}

	@DeleteMapping("/{utenteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID utenteId) {
		us.deleteUtente(utenteId);
	}

}
