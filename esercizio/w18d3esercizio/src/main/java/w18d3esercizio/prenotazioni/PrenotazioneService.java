package w18d3esercizio.prenotazioni;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import w18d3esercizio.exceptions.BadRequestException;
import w18d3esercizio.exceptions.NotFoundException;
import w18d3esercizio.postazioni.Postazione;
import w18d3esercizio.postazioni.PostazioneService;
import w18d3esercizio.utenti.Utente;
import w18d3esercizio.utenti.UtenteService;

@Service
public class PrenotazioneService {
	private final PrenotazioneRepository pr;
	private final UtenteService us;
	private final PostazioneService ps;

	@Autowired
	public PrenotazioneService(PrenotazioneRepository pr, UtenteService us, PostazioneService ps) {
		this.pr = pr;
		this.us = us;
		this.ps = ps;
	}

	public Prenotazione creaPrenotazione(PrenotazioneRequestPayload body) {
		Utente utente = us.getUtenteById(body.getUtenteId());
		Postazione postazione = ps.getPostazioneById(body.getPostazioneId());
		if (utenteDisponibile(body.getGiornoPrenotazione(), utente)
				&& postazioneDisponibile(body.getGiornoPrenotazione(), postazione)
				&& dataInRange(body.getGiornoPrenotazione())) {
			Prenotazione prenotazione = new Prenotazione(body.getGiornoPrenotazione(), postazione, utente);
			return pr.save(prenotazione);
		} else {
			throw new BadRequestException("Impossibile procedere con la prenotazione");
		}

	}

	public Page<Prenotazione> getPrenotazioni(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return pr.findAll(pageable);
	}

	public Prenotazione getPrenotazioneById(UUID prenotazioneId) throws NotFoundException {
		return pr.findById(prenotazioneId).orElseThrow(() -> new NotFoundException(prenotazioneId));
	}

	public Prenotazione getPrenotazioneByIdAndUpdate(UUID prenotazioneId, PrenotazioneRequestPayload body) {
		Prenotazione found = this.getPrenotazioneById(prenotazioneId);
		Utente utente = us.getUtenteById(body.getUtenteId());
		Postazione postazione = ps.getPostazioneById(body.getPostazioneId());
		found.setGiornoPrenotazione(body.getGiornoPrenotazione());
		found.setPostazione(postazione);
		found.setUtente(utente);
		return pr.save(found);
	}

	public void deletePrenotazione(UUID prenotazioneId) {
		Prenotazione found = this.getPrenotazioneById(prenotazioneId);
		pr.delete(found);
	}

	public boolean utenteDisponibile(LocalDate giornoPrenotazione, Utente utente) {
		return pr.findByGiornoPrenotazioneAndUtente(giornoPrenotazione, utente).isEmpty();
	}

	public boolean postazioneDisponibile(LocalDate giornoPrenotazione, Postazione postazione) {
		return pr.findByGiornoPrenotazioneAndPostazione(giornoPrenotazione, postazione).isEmpty();
	}

	public boolean dataInRange(LocalDate giornoPrenotazione) {
		boolean result = LocalDate.now().isBefore(giornoPrenotazione.minusDays(2));
		System.out.println("Data corretta: " + result);
		return result;
	}

}
