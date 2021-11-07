package it.prova.societaimpiegatoprogetto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.societaimpiegatoprogetto.service.BatteriaDiTestService;

@SpringBootApplication
public class SocietaimpiegatoprogettoApplication implements CommandLineRunner {

	@Autowired
	private BatteriaDiTestService batteriaDiTestService;

	public static void main(String[] args) {
		SpringApplication.run(SocietaimpiegatoprogettoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("############################ INIZIO BATTERIA DI TEST ############################");
		System.out.println();

		batteriaDiTestService.testInserimentoSocieta();
		System.out.println();
		batteriaDiTestService.testFindByExample();
		System.out.println();
		batteriaDiTestService.testRimozioneSocieta();
		System.out.println();
		batteriaDiTestService.testInserimentoProgetto();
		System.out.println();
		batteriaDiTestService.testCollegamentoDiImpiegatoAPiuProgetti();
		System.out.println();
		batteriaDiTestService.testCollegamentoDiProgettoAPiuImpiegati();
		System.out.println();
		System.out.println("errore qui?");
		batteriaDiTestService.testStampaClienti();
		System.out.println();
		batteriaDiTestService.testNomiSocietaConProgettoDurataPiuDiUnAnno();
		System.out.println();
		batteriaDiTestService.testCercaProgettiConImpiegatoConRal();
		System.out.println();
		batteriaDiTestService.testImpiegatoAnzianoSocietaFondatePrimaCheLavoraAProgettoPiuDi();
		System.out.println("############################ FINE BATTERIA DI TEST ##############################");
	}

}
