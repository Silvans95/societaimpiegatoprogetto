package it.prova.societaimpiegatoprogetto.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.societaimpiegatoprogetto.model.Progetto;
import it.prova.societaimpiegatoprogetto.model.Societa;
import it.prova.societaimpiegatoprogetto.repository.SocietaRepository;

@Service
public class SocietaServiceImpl implements SocietaService {

	@Autowired
	private SocietaRepository societaRepository;
	@Autowired
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Societa> listAllSocieta() {
		return (List<Societa>) societaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Societa caricaSingoloSocieta(Long id) {
		return societaRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Societa societaInstance) {
		societaRepository.save(societaInstance);
	}

	@Transactional
	public void inserisciNuovo(Societa societaInstance) {
		societaRepository.save(societaInstance);
	}

	@Transactional
	public void rimuovi(Societa societaInstance) {
		societaRepository.delete(societaInstance);
	}

	@Override
	public List<Societa> findByExample(Societa example) {

		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select r from Societa r where r.id = r.id ");

		if (StringUtils.isEmpty(example.getRagioneSociale())) {
			whereClauses.add(" r.ragioneSociale  like :ragioneSociale ");
			paramaterMap.put("ragioneSociale", "%" + example.getRagioneSociale() + "%");
		}
		if (example.getDataFondazione() != null) {
			whereClauses.add(" r.dataFondazione >= :dataFondazione ");
			paramaterMap.put("dataFondazione", "%" + example.getDataFondazione() + "%");
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Societa> typedQuery = entityManager.createQuery(queryBuilder.toString(), Societa.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

	@Transactional(readOnly = true)
	public List<Progetto> cercaByProgettiDiUnaSocieta(Societa term) {
		return societaRepository.findAllByImpiegati(term);
	}

	@Transactional(readOnly = true)
	public List<Societa> cercaProgettiPiuDiUnAnno() {

		return societaRepository.findAllByDurata();
	}

}
