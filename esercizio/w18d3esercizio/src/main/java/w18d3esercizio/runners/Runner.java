package w18d3esercizio.runners;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import w18d3esercizio.postazioni.PostazioneRequestPayload;
import w18d3esercizio.postazioni.PostazioneService;
import w18d3esercizio.postazioni.TipoPostazione;
import w18d3esercizio.prenotazioni.PrenotazioneRequestPayload;
import w18d3esercizio.prenotazioni.PrenotazioneService;
import w18d3esercizio.utenti.UtenteRequestPayload;
import w18d3esercizio.utenti.UtenteService;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

	@Autowired
	private UtenteService us;

	@Autowired
	private PostazioneService ps;

	@Autowired
	private PrenotazioneService prs;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		Random rnd = new Random();

		for (int i = 0; i < 30; i++) {
			String userName = faker.dragonBall().character();
			String nomeCompleto = faker.name().fullName();
			String email = faker.internet().emailAddress();
			UtenteRequestPayload rndUser = new UtenteRequestPayload(userName, nomeCompleto, email);
//			us.creaUtente(rndUser);
		}

		for (int i = 0; i < 20; i++) {
			String descrizione = faker.lorem().sentence();
			TipoPostazione tipoPostazione = TipoPostazione.values()[rnd.nextInt(TipoPostazione.values().length)];
			int numeroMassimo = rnd.nextInt(100) + 10;
			String citta = faker.address().city();
			PostazioneRequestPayload rndPostazione = new PostazioneRequestPayload(descrizione, tipoPostazione,
					numeroMassimo, citta);
//			ps.creaPostazione(rndPostazione);

		}

		for (int i = 0; i < 100; i++) {
			UUID utenteId = us.getRndUtente();
			UUID postazioneId = ps.getRandomPostazione();
			int rndInt = rnd.nextInt(30);
			try {
				PrenotazioneRequestPayload body = new PrenotazioneRequestPayload(LocalDate.now().plusDays(rndInt),
						utenteId, postazioneId);

				prs.creaPrenotazione(body);
			} catch (Exception e) {
				log.info("Prentoazione non riuscita: " + e.getMessage());
			}

		}

	}

}
