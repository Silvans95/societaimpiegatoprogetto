package it.prova.societaimpiegatoprogetto.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.societaimpiegatoprogetto.model.Impiegato;
import it.prova.societaimpiegatoprogetto.model.Progetto;
import it.prova.societaimpiegatoprogetto.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private ImpiegatoService impiegatoService;
	@Autowired
	private SocietaService societaService;
	@Autowired
	private ProgettoService progettoService;

	public void testInserimentoSocieta() {
		societaService.inserisciNuovo(new Societa("SolvingTeam", new Date()));
		if (societaService.listAllSocieta().size() != 1) {
			throw new RuntimeException("non ho inserito correttamente la societa");
		}
		System.out.println(
				"ho inserito la societa di nome " + societaService.caricaSingoloSocieta(1L).getRagioneSociale());
	}

	public void testFindByExample() {

		String ragioneToken = "ragioneSociale";
		Date dataFondazione = new Date();

		IntStream.range(1, 4).forEach(i -> {
			Societa nuovaSocieta = new Societa(ragioneToken + i, dataFondazione);
			societaService.inserisciNuovo(nuovaSocieta);
		});

		if (societaService.listAllSocieta().size() != 4) {
			throw new RuntimeException("Qualcosa è andato storto");
		}
		System.out.println(societaService.findByExample(new Societa("ragione")).size());
	}

	public void testRimozioneSocieta() {
		Societa societaDaRimuovere = new Societa("societàDaRimuovere", new Date());
		societaService.inserisciNuovo(societaDaRimuovere);
		societaService.rimuovi(societaDaRimuovere);
		if (societaService.caricaSingoloSocieta(5L) != null) {
			throw new RuntimeException("Qualcosa nella rimozione è andata storta");
		}
	}

	public void testInserimentoProgetto() {

		progettoService.inserisciNuovo(new Progetto("Nome1", "Cliente1", 1));
		System.out.println("ho appena inserito il progetto di " + progettoService.caricaSingoloProgetto(1L).getNome());

	}

	public void testCollegamentoDiImpiegatoAPiuProgetti() {

		Societa societa = new Societa("ROMA", new Date());
		societaService.inserisciNuovo(societa);

		Impiegato impiegatotoken = new Impiegato("Nome 1", "Cognome 1", new Date(), 10000, societa);

		String nomeToken = "nomeToken";
		String clienteToken = "clienteToken";
		Set<Progetto> progetti = new HashSet<>(0);
		IntStream.range(4, 7).forEach(i -> {
			Progetto nuovoProgetto = new Progetto(nomeToken + i, clienteToken, i);
			progetti.add(nuovoProgetto);

			progettoService.inserisciNuovo(nuovoProgetto);
		});
		impiegatotoken.setProgetti(progetti);
		impiegatoService.inserisciNuovo(impiegatotoken);
	}

	public void testCollegamentoDiProgettoAPiuImpiegati() {

		Societa societa = new Societa("ROMA2", new Date());
		societaService.inserisciNuovo(societa);

		Set<Progetto> progetti = new HashSet<>(0);
		Progetto nuovoProgetto = new Progetto("nomeProgetto", "clienteProgetto", 50);
		progetti.add(nuovoProgetto);
		progettoService.inserisciNuovo(nuovoProgetto);

		String nomeToken = "nomeToken";
		String cognomeToken = "cognomeToken";

		IntStream.range(4, 7).forEach(i -> {
			Impiegato nuovoImpiegato = new Impiegato(nomeToken + i, cognomeToken + i, new Date(), 1000, societa);

			nuovoImpiegato.setProgetti(progetti);
			impiegatoService.inserisciNuovo(nuovoImpiegato);
		});
	}

	public void testStampaClienti() {

		Societa societa = new Societa("ROMA 3", new Date());
		societaService.inserisciNuovo(societa);

		Progetto progetto = new Progetto("progetto1", "cliente1", 5);
		Progetto progetto2 = new Progetto("progetto2", "cliente2", 8);
		Progetto progetto3 = new Progetto("progetto3", "cliente3", 3);
		progettoService.inserisciNuovo(progetto);
		progettoService.inserisciNuovo(progetto2);
		progettoService.inserisciNuovo(progetto3);

		Impiegato impiegato = new Impiegato("nome10", "cognome10", new Date(), 50000, societa);
		impiegatoService.inserisciNuovo(impiegato);

		impiegato.addToProgetti(progetto);
		impiegato.addToProgetti(progetto2);
		impiegato.addToProgetti(progetto3);

		impiegatoService.aggiorna(impiegato);

		List<String> clienti = progettoService.caricaListaClientiDataSocieta(societa);

		if (clienti.size() != 3)
			throw new RuntimeException("c'è stato un problema");

		clienti.forEach(p -> System.out.println(p));

	}

	public void testNomiSocietaConProgettoDurataPiuDiUnAnno() {
		Societa societa = new Societa("Francy", new Date("1920/12/11"));
		societaService.inserisciNuovo(societa);

		Societa societa2 = new Societa("Raland", new Date("1980/09/02"));
		societaService.inserisciNuovo(societa2);

		Progetto progetto = new Progetto("progetto1", "cliente1", 10);
		Progetto progetto2 = new Progetto("progetto2", "cliente2", 13);
		progettoService.inserisciNuovo(progetto);
		progettoService.inserisciNuovo(progetto2);

		Impiegato impiegato = new Impiegato("nome1", "cognome1", new Date("2002/01/01"), 2000000, societa);
		impiegatoService.inserisciNuovo(impiegato);
		Impiegato impiegato2 = new Impiegato("nome2", "cognome2", new Date("2020/02/01"), 20000, societa2);
		impiegatoService.inserisciNuovo(impiegato2);

		impiegato.addToProgetti(progetto);
		impiegato.addToProgetti(progetto2);

		impiegatoService.aggiorna(impiegato);
		impiegatoService.aggiorna(impiegato2);

		List<Societa> societa1 = societaService.cercaProgettiPiuDiUnAnno();

		societa1.stream().forEach(s -> System.out.println(s.getRagioneSociale()));
	}

	public void testCercaProgettiConImpiegatoConRal() {

		Societa societa = new Societa("Roma4", new Date());
		societaService.inserisciNuovo(societa);

		Progetto progetto1 = new Progetto("progetto1", "cliente1", 10);
		Progetto progetto2 = new Progetto("progetto2", "cliente2", 103);
		progettoService.inserisciNuovo(progetto1);
		progettoService.inserisciNuovo(progetto2);

		Impiegato impiegato1 = new Impiegato("nome1", "cognome1", new Date(), 300000, societa);
		impiegatoService.inserisciNuovo(impiegato1);
		Impiegato impiegato2 = new Impiegato("nome2", "cognome2", new Date(), 7000, societa);
		impiegatoService.inserisciNuovo(impiegato2);

		impiegato1.addToProgetti(progetto1);
		impiegato2.addToProgetti(progetto2);

		impiegatoService.aggiorna(impiegato1);
		impiegatoService.aggiorna(impiegato2);

		List<Progetto> progetti = progettoService.cercaImpiegatoConRal(7000);

		progetti.stream().forEach(p -> System.out.println(p.getNome()));

	}

	public void testImpiegatoAnzianoSocietaFondatePrimaCheLavoraAProgettoPiuDi() {

		Societa societa = new Societa("Roma10", new Date());
		societaService.inserisciNuovo(societa);

		Progetto progetto = new Progetto("progetto10", "cliente10", 10);
		progettoService.inserisciNuovo(progetto);

		Impiegato impiegato1 = new Impiegato("nome10", "cognome10", new Date("2000/01/04"), 2993, societa);
		impiegatoService.inserisciNuovo(impiegato1);
		Impiegato impiegato2 = new Impiegato("nome 20", "cognome 20", new Date("2013/08/23"), 90000, societa);
		impiegatoService.inserisciNuovo(impiegato2);

		impiegato1.addToProgetti(progetto);
		impiegato2.addToProgetti(progetto);

		impiegatoService.aggiorna(impiegato1);
		impiegatoService.aggiorna(impiegato2);

		impiegatoService.impiegatoPiuAnzianoSocietaFondatePrimaCheLavoraAProgettoPiuDi();
	}

}
