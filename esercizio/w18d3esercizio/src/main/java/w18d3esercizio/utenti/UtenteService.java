package w18d3esercizio.utenti;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import w18d3esercizio.exceptions.NotFoundException;

@Service
public class UtenteService {
	private final UtenteRepository ur;

	@Autowired
	public UtenteService(UtenteRepository ur) {
		this.ur = ur;
	}

	public Utente creaUtente(UtenteRequestPayload body) {
		Utente utente = new Utente(body.getUserName(), body.getNomeCompleto(), body.getEmail());
		return ur.save(utente);
	}

	public Page<Utente> getUtenti(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return ur.findAll(pageable);
	}

	public Utente getUtenteById(UUID utenteId) throws NotFoundException {
		return ur.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
	}

	public Utente getUtenteByIdAndUpdate(UUID utenteId, UtenteRequestPayload body) {
		Utente found = this.getUtenteById(utenteId);
		found.setUserName(body.getUserName());
		found.setNomeCompleto(body.getNomeCompleto());
		found.setEmail(body.getEmail());

		return ur.save(found);
	}

	public void deleteUtente(UUID utenteId) {
		Utente found = this.getUtenteById(utenteId);
		ur.delete(found);
	}

	public UUID getRndUtente() {
		Random rnd = new Random();
		List<Utente> utenti = ur.findAll();
		if (utenti.isEmpty()) {
			return null;
		}
		return utenti.get(rnd.nextInt(utenti.size())).getUtenteId();
	}
}
