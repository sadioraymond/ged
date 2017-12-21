package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FicheSortie implements Serializable{
	@Id @GeneratedValue
	private Long id_fichesortie;
	private String code_fiche;
	private String libelle;
	@OneToMany(mappedBy="fichesorties", fetch= FetchType.LAZY)
	private Collection<DetailFiche> detailfiches;
	private Date date;
	private int etat;
	public FicheSortie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FicheSortie(String code_fiche, String libelle) {
		super();
		this.code_fiche = code_fiche;
		this.libelle = libelle;
	}
	
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getId_fichesortie() {
		return id_fichesortie;
	}
	public void setId_fichesortie(Long id_fichesortie) {
		this.id_fichesortie = id_fichesortie;
	}
	public String getCode_fiche() {
		return code_fiche;
	}
	public void setCode_fiche(String code_fiche) {
		this.code_fiche = code_fiche;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Collection<DetailFiche> getDetailfiches() {
		return detailfiches;
	}
	public void setDetailfiches(Collection<DetailFiche> detailfiches) {
		this.detailfiches = detailfiches;
	}
	
	
}
