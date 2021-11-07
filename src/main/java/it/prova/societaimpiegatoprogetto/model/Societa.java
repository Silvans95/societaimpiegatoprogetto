package it.prova.societaimpiegatoprogetto.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "societa")
public class Societa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "ragioneSociale")
	private String ragioneSociale;
	@Column(name = "dataFondazione")
	private Date dataFondazione;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "societa")
	private Set<Impiegato> impiegati = new HashSet<>();

	public Societa() {
		// TODO Auto-generated constructor stub
	}

	public Societa(Long id, String ragioneSociale, Date dataFondazione, Set<Impiegato> impiegati) {
		super();
		this.id = id;
		this.ragioneSociale = ragioneSociale;
		this.dataFondazione = dataFondazione;
		this.impiegati = impiegati;
	}

	public Societa(String ragioneSociale, Date dataFondazione) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.dataFondazione = dataFondazione;
	}

	public Societa(String ragioneSociale) {
		super();
		this.ragioneSociale = ragioneSociale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public Date getDataFondazione() {
		return dataFondazione;
	}

	public void setDataFondazione(Date dataFondazione) {
		this.dataFondazione = dataFondazione;
	}

	public Set<Impiegato> getImpiegati() {
		return impiegati;
	}

	public void setImpiegati(Set<Impiegato> impiegati) {
		this.impiegati = impiegati;
	}

}
