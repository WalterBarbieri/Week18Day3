package w18d3esercizio.postazioni;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostazioneRequestPayload {
	private String descrizione;
	private TipoPostazione tipoPostazione;
	private int numeroMassimo;
	private String citta;
}
