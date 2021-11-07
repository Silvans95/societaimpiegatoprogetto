package it.prova.societaimpiegatoprogetto.service;

import java.util.List;

import it.prova.societaimpiegatoprogetto.model.Progetto;
import it.prova.societaimpiegatoprogetto.model.Societa;

public interface ProgettoService {

	public List<Progetto> listAllProgetti();

	public Progetto caricaSingoloProgetto(Long id);

	public void aggiorna(Progetto progettoInstance);

	public void inserisciNuovo(Progetto progettoInstance);

	public void rimuovi(Progetto progettoInstance);
	
	public List<String> caricaListaClientiDataSocieta(Societa societaInstance);

	public List<Progetto> cercaImpiegatoConRal(int ral);

}
