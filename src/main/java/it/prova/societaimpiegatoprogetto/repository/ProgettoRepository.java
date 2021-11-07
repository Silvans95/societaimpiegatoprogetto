package it.prova.societaimpiegatoprogetto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.societaimpiegatoprogetto.model.Progetto;
import it.prova.societaimpiegatoprogetto.model.Societa;

public interface ProgettoRepository extends CrudRepository<Progetto, Long>, QueryByExampleExecutor<Progetto> {


	@Query("select distinct p.cliente from Societa s join s.impiegati i join i.progetti p")
	List<String> caricaListaClientiDataSocieta(Societa societaInput);

	List<Progetto> findAllDistinctByImpiegati_RalIs(int ral);
	
}
