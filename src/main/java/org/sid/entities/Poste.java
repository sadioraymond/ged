package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Poste implements Serializable {
	@Id @GeneratedValue
	private Long id_poste;
	private String code_poste;
	private String libelle;
	@OneToMany(mappedBy="postes", fetch= FetchType.LAZY)
	private Collection<FichePoste> fichepostes;
	@ManyToOne
	@JoinColumn(name="id_service")
	private Service services;
	public Poste() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Poste(String code_poste, String libelle) {
		super();
		this.code_poste = code_poste;
		this.libelle = libelle;
	}
	public Long getId_poste() {
		return id_poste;
	}
	public void setId_poste(Long id_poste) {
		this.id_poste = id_poste;
	}
	public String getCode_poste() {
		return code_poste;
	}
	public void setCode_poste(String code_poste) {
		this.code_poste = code_poste;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Collection<FichePoste> getFichepostes() {
		return fichepostes;
	}
	public void setFichepostes(Collection<FichePoste> fichepostes) {
		this.fichepostes = fichepostes;
	}
	public Service getServices() {
		return services;
	}
	public void setServices(Service services) {
		this.services = services;
	}
	public Poste(String code_poste, String libelle, Service services) {
		super();
		this.code_poste = code_poste;
		this.libelle = libelle;
		this.services = services;
	}
	
	
}
