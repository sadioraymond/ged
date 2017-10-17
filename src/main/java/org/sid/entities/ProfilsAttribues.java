package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProfilsAttribues implements Serializable{
	@Id @GeneratedValue
	private Long id_profilattribues;
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateurs;
	@ManyToOne
	@JoinColumn(name="id_profil")
	private Profil profils;
	private Date datedeb;
	private Date datefin;
	public ProfilsAttribues() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProfilsAttribues(Utilisateur utilisateurs, Profil profils, Date datedeb, Date datefin) {
		super();
		this.utilisateurs = utilisateurs;
		this.profils = profils;
		this.datedeb = datedeb;
		this.datefin = datefin;
	}
	public Long getId_profilattribues() {
		return id_profilattribues;
	}
	public void setId_profilattribues(Long id_profilattribues) {
		this.id_profilattribues = id_profilattribues;
	}
	public Utilisateur getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Utilisateur utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public Profil getProfils() {
		return profils;
	}
	public void setProfils(Profil profils) {
		this.profils = profils;
	}
	public Date getDatedeb() {
		return datedeb;
	}
	public void setDatedeb(Date datedeb) {
		this.datedeb = datedeb;
	}
	public Date getDatefin() {
		return datefin;
	}
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	
}
