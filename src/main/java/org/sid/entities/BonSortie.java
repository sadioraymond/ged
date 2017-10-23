package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BonSortie implements Serializable{
	@Id @GeneratedValue
	private Long id_bonsortie;
	private String code_bonsortie;
	private Date date;
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateurs;
	@ManyToOne
	@JoinColumn(name="id_demandeappro")
	private DemandeAppro demandeappros;
	@ManyToOne
	@JoinColumn(name="id_detailfiche")
	private DetailFiche detailfiches;
	public BonSortie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId_bonsortie() {
		return id_bonsortie;
	}
	public void setId_bonsortie(Long id_bonsortie) {
		this.id_bonsortie = id_bonsortie;
	}
	public String getCode_bonsortie() {
		return code_bonsortie;
	}
	public void setCode_bonsortie(String code_bonsortie) {
		this.code_bonsortie = code_bonsortie;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Utilisateur getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Utilisateur utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	public DemandeAppro getDemandeappros() {
		return demandeappros;
	}
	public void setDemandeappros(DemandeAppro demandeappros) {
		this.demandeappros = demandeappros;
	}
	public DetailFiche getDetailfiches() {
		return detailfiches;
	}
	public void setDetailfiches(DetailFiche detailfiches) {
		this.detailfiches = detailfiches;
	}
	public BonSortie(String code_bonsortie, Date date, Utilisateur utilisateurs, DemandeAppro demandeappros,
			DetailFiche detailfiches) {
		super();
		this.code_bonsortie = code_bonsortie;
		this.date = date;
		this.utilisateurs = utilisateurs;
		this.demandeappros = demandeappros;
		this.detailfiches = detailfiches;
	}
	

}
