package w18d3esercizio.postazioni;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "postazioni")
@Getter
@Setter
@NoArgsConstructor
public class Postazione {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID postazioneId;
	private String descrizione;
	@Enumerated(EnumType.STRING)
	private TipoPostazione tipoPostazione;
	private int numeroMassimo;
	private String citta;

//	@OneToMany(mappedBy = "postazione")
//	private List<Prenotazione> prenotazione;

	public Postazione(String descrizione, TipoPostazione tipoPostazione, int numeroMassimo, String citta) {
		this.setDescrizione(descrizione);
		this.setTipoPostazione(tipoPostazione);
		this.setNumeroMassimo(numeroMassimo);
		this.setCitta(citta);
	}

	@Override
	public String toString() {
		return "Postazione [Id: " + postazioneId + ", Descrizione: " + descrizione + ", Tipo Postazione: "
				+ tipoPostazione + ", Numero Massimo Occupanti: " + numeroMassimo + "]";
	}

}
