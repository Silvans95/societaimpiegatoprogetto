package it.prova.societaimpiegatoprogetto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.societaimpiegatoprogetto.model.Progetto;
import it.prova.societaimpiegatoprogetto.model.Societa;
import it.prova.societaimpiegatoprogetto.repository.ProgettoRepository;

@Service
public class ProgettoServiceImpl implements ProgettoService {

	@Autowired
	private ProgettoRepository progettoRepository;

	@Transactional(readOnly = true)
	public List<Progetto> listAllProgetti() {
		return (List<Progetto>) progettoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Progetto caricaSingoloProgetto(Long id) {
		return progettoRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Progetto progettoInstance) {
		progettoRepository.save(progettoInstance);
	}

	@Transactional
	public void inserisciNuovo(Progetto progettoInstance) {
		progettoRepository.save(progettoInstance);
	}

	@Transactional
	public void rimuovi(Progetto progettoInstance) {
		progettoRepository.delete(progettoInstance);
	}

	@Transactional(readOnly = true)
	public List<String> caricaListaClientiDataSocieta(Societa societaInstance) {
		return progettoRepository.caricaListaClientiDataSocieta(societaInstance);

	}

	@Transactional
	public List<Progetto> cercaImpiegatoConRal(int ral) {
		return progettoRepository.findAllDistinctByImpiegati_RalIs(ral);
	}
}
