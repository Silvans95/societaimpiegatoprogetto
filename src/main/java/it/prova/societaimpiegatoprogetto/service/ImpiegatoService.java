package it.prova.societaimpiegatoprogetto.service;

import java.util.List;

import it.prova.societaimpiegatoprogetto.model.Impiegato;


public interface ImpiegatoService {

	public List<Impiegato> listAllImpiegati();

	public Impiegato caricaSingoloImpiegato(Long id);

	public void aggiorna(Impiegato inpiegatoInstance);

	public void inserisciNuovo(Impiegato inpiegatoInstance);

	public void rimuovi(Impiegato inpiegatoInstance);

	public List<Impiegato> impiegatoPiuAnzianoSocietaFondatePrimaCheLavoraAProgettoPiuDi();


}
