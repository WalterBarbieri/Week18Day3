package w18d3esercizio.prenotazioni;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import w18d3esercizio.postazioni.Postazione;
import w18d3esercizio.utenti.Utente;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
	Optional<Prenotazione> findByGiornoPrenotazioneAndPostazione(LocalDate giornoPrenotazione, Postazione postazione);

	Optional<Prenotazione> findByGiornoPrenotazioneAndUtente(LocalDate giornoPrenotazione, Utente utente);

}
