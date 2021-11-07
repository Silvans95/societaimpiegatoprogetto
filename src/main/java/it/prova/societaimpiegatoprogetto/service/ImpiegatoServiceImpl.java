package it.prova.societaimpiegatoprogetto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.societaimpiegatoprogetto.model.Impiegato;
import it.prova.societaimpiegatoprogetto.repository.ImpiegatoRepository;

@Service
public class ImpiegatoServiceImpl implements ImpiegatoService {

	@Autowired
	private ImpiegatoRepository impiegatoRepository;

	@Transactional(readOnly = true)
	public List<Impiegato> listAllImpiegati() {
		return (List<Impiegato>) impiegatoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Impiegato caricaSingoloImpiegato(Long id) {
		return impiegatoRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Impiegato impiegatoInstance) {
		impiegatoRepository.save(impiegatoInstance);
	}

	@Transactional
	public void inserisciNuovo(Impiegato impiegatoInstance) {
		impiegatoRepository.save(impiegatoInstance);
	}

	@Transactional
	public void rimuovi(Impiegato impiegatoInstance) {
		impiegatoRepository.delete(impiegatoInstance);
	}

	@Transactional
	public List<Impiegato> impiegatoPiuAnzianoSocietaFondatePrimaCheLavoraAProgettoPiuDi() {
		return impiegatoRepository.impiegatoAnzianoSocietaPrimaCheLavoraAProgettoPiuDi();
	}

}