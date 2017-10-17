package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Service implements Serializable{
	@Id @GeneratedValue
	private Long id_service;
	private String code_service;
	private String libelle;
	@OneToMany(mappedBy="services", fetch= FetchType.LAZY)
	private Collection<Poste> postes;
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Service(String code_service, String libelle) {
		super();
		this.code_service = code_service;
		this.libelle = libelle;
	}
	public Long getId_service() {
		return id_service;
	}
	public void setId_service(Long id_service) {
		this.id_service = id_service;
	}
	public String getCode_service() {
		return code_service;
	}
	public void setCode_service(String code_service) {
		this.code_service = code_service;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Collection<Poste> getPostes() {
		return postes;
	}
	public void setPostes(Collection<Poste> postes) {
		this.postes = postes;
	}
	
}
