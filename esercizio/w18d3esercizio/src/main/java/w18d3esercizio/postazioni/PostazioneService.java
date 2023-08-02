package w18d3esercizio.postazioni;

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
public class PostazioneService {
	private final PostazioneRepository pr;

	@Autowired
	public PostazioneService(PostazioneRepository pr) {
		this.pr = pr;
	}

	public Postazione creaPostazione(PostazioneRequestPayload body) {
		Postazione postazione = new Postazione(body.getDescrizione(), body.getTipoPostazione(), body.getNumeroMassimo(),
				body.getCitta());
		return pr.save(postazione);
	}

	public Page<Postazione> getPostazioni(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return pr.findAll(pageable);
	}

	public Postazione getPostazioneById(UUID postazioneId) throws NotFoundException {
		return pr.findById(postazioneId).orElseThrow(() -> new NotFoundException(postazioneId));
	}

	public Postazione getPostazioneByIdAndUpdate(UUID postazioneId, PostazioneRequestPayload body) {
		Postazione found = this.getPostazioneById(postazioneId);
		found.setDescrizione(body.getDescrizione());
		found.setTipoPostazione(body.getTipoPostazione());
		found.setNumeroMassimo(body.getNumeroMassimo());
		found.setCitta(body.getCitta());

		return pr.save(found);

	}

	public void deletePostazione(UUID postazioneId) {
		Postazione found = this.getPostazioneById(postazioneId);
		pr.delete(found);
	}

	public List<Postazione> findByTipoAndCitta(TipoPostazione tipoPostazione, String citta) {
		return pr.findAllByTipoPostazioneAndCitta(tipoPostazione, citta);
	}

	public UUID getRandomPostazione() {
		Random rnd = new Random();
		List<Postazione> postazioni = pr.findAll();
		if (postazioni.isEmpty()) {
			return null;
		}
		return postazioni.get(rnd.nextInt(postazioni.size())).getPostazioneId();
	}

}
