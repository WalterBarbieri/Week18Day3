package w18d3esercizio.prenotazioni;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrenotazioneRequestPayload {
	private LocalDate giornoPrenotazione;
	private UUID utenteId;
	private UUID postazioneId;
}
