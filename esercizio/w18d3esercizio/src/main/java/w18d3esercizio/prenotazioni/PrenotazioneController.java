package w18d3esercizio.prenotazioni;

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
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
	private final PrenotazioneService ps;

	@Autowired
	public PrenotazioneController(PrenotazioneService ps) {
		this.ps = ps;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Prenotazione crea(@RequestBody PrenotazioneRequestPayload body) {
		Prenotazione prenotazione = ps.creaPrenotazione(body);
		return prenotazione;
	}

	@GetMapping
	public Page<Prenotazione> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "prenotazioneId") String sortBy) {
		return ps.getPrenotazioni(page, size, sortBy);

	}

	@GetMapping("/{prenotazioneId}")
	public Prenotazione getUtente(@PathVariable UUID prenotazioneId) {
		return ps.getPrenotazioneById(prenotazioneId);
	}

	@PutMapping("/{prenotazioneId}")
	public Prenotazione getUtenteUpdate(@PathVariable UUID prenotazioneId,
			@RequestBody PrenotazioneRequestPayload body) {
		return ps.getPrenotazioneByIdAndUpdate(prenotazioneId, body);
	}

	@DeleteMapping("/{prenotazioneId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID prenotazioneId) {
		ps.deletePrenotazione(prenotazioneId);
	}

}
