package w18d3esercizio.postazioni;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, UUID> {
	List<Postazione> findAllByTipoPostazioneAndCitta(TipoPostazione tipoPostazione, String citta);
}
