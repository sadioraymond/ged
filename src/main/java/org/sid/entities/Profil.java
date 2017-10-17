package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Profil implements Serializable{
	@Id @GeneratedValue
	private Long id_profil;
	private String libelle;
	private String code_profil;
	@OneToMany(mappedBy="profils", fetch= FetchType.LAZY)
	private Collection<ProfilsAttribues> profilsattribues;
	public Profil() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Profil(String libelle, String code_profil) {
		super();
		this.libelle = libelle;
		this.code_profil = code_profil;
	}
	public Long getId_profil() {
		return id_profil;
	}
	public void setId_profil(Long id_profil) {
		this.id_profil = id_profil;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getCode_profil() {
		return code_profil;
	}
	public void setCode_profil(String code_profil) {
		this.code_profil = code_profil;
	}
	public Collection<ProfilsAttribues> getProfilsattribues() {
		return profilsattribues;
	}
	public void setProfilsattribues(Collection<ProfilsAttribues> profilsattribues) {
		this.profilsattribues = profilsattribues;
	}
	
}
