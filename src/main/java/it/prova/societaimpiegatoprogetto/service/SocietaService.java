package it.prova.societaimpiegatoprogetto.service;

import java.util.List;

import it.prova.societaimpiegatoprogetto.model.Progetto;
import it.prova.societaimpiegatoprogetto.model.Societa;

public interface SocietaService {

	public List<Societa> listAllSocieta();

	public Societa caricaSingoloSocieta(Long id);

	public void aggiorna(Societa societaInstance);

	public void inserisciNuovo(Societa societaInstance);

	public void rimuovi(Societa societaInstance);

	List<Societa> findByExample(Societa example);
	
	public List<Progetto> cercaByProgettiDiUnaSocieta(Societa term);

	public List<Societa> cercaProgettiPiuDiUnAnno();

}
