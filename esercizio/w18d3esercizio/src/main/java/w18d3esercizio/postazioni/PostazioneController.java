package w18d3esercizio.postazioni;

import java.util.List;
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
@RequestMapping("/postazioni")
public class PostazioneController {
	private final PostazioneService ps;

	@Autowired
	public PostazioneController(PostazioneService ps) {
		this.ps = ps;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Postazione crea(@RequestBody PostazioneRequestPayload body) {
		Postazione postazione = ps.creaPostazione(body);
		return postazione;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.FOUND)
	public Page<Postazione> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "postazioneId") String sortBy) {
		return ps.getPostazioni(page, size, sortBy);

	}

	@GetMapping("/{postazioneId}")
	@ResponseStatus(HttpStatus.FOUND)
	public Postazione getPostazioneById(@PathVariable UUID postazioneId) {
		return ps.getPostazioneById(postazioneId);
	}

	@PutMapping("/{postazioneId}")
	public Postazione getPostazioneByIdUpdate(@PathVariable UUID postazioneId,
			@RequestBody PostazioneRequestPayload body) {
		return ps.getPostazioneByIdAndUpdate(postazioneId, body);
	}

	@DeleteMapping("/{postazioneId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePostazione(@PathVariable UUID postazioneId) {
		ps.deletePostazione(postazioneId);
	}

	@GetMapping("/search")
	@ResponseStatus(HttpStatus.FOUND)
	public List<Postazione> getPostazioneByTipoAndCitta(@RequestParam TipoPostazione tipoPostazione,
			@RequestParam String citta) {
		return ps.findByTipoAndCitta(tipoPostazione, citta);
	}

}
