package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FichePoste implements Serializable {
	@Id @GeneratedValue
	private Long id_ficheposte;
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateurs;
	@ManyToOne
	@JoinColumn(name="id_poste")
	private Poste postes;
	private Date datedeb;
	private Date datefin;
	public Utilisateur getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Utilisateur utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public Poste getPostes() {
		return postes;
	}
	public void setPostes(Poste postes) {
		this.postes = postes;
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
	public FichePoste() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId_ficheposte() {
		return id_ficheposte;
	}
	public void setId_ficheposte(Long id_ficheposte) {
		this.id_ficheposte = id_ficheposte;
	}
	public FichePoste(Utilisateur utilisateurs, Poste postes, Date datedeb, Date datefin) {
		super();
		this.utilisateurs = utilisateurs;
		this.postes = postes;
		this.datedeb = datedeb;
		this.datefin = datefin;
	}
	
}
