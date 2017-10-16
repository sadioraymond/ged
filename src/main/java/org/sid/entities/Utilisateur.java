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
public class Utilisateur implements Serializable{
	@Id @GeneratedValue
	private Long id_user;
	private String code_user;
	private String nom_user;
	private String prenom_user;
	private String adresse;
	private String tel;
	private Date datenaiss;
	private String sexe;
	private String situation_matri;
	private String email;
	@OneToMany(mappedBy="utilisateurs", fetch= FetchType.LAZY)
	private Collection<Documents> documentss;
	@OneToMany(mappedBy="utilisateurs", fetch= FetchType.LAZY)
	private Collection<DroitAttribues> droitattribues;
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Utilisateur(String code_user, String nom_user, String prenom_user, String adresse, String tel,
			Date datenaiss, String sexe, String situation_matri, String email) {
		super();
		this.code_user = code_user;
		this.nom_user = nom_user;
		this.prenom_user = prenom_user;
		this.adresse = adresse;
		this.tel = tel;
		this.datenaiss = datenaiss;
		this.sexe = sexe;
		this.situation_matri = situation_matri;
		this.email = email;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getCode_user() {
		return code_user;
	}
	public void setCode_user(String code_user) {
		this.code_user = code_user;
	}
	public String getNom_user() {
		return nom_user;
	}
	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}
	public String getPrenom_user() {
		return prenom_user;
	}
	public void setPrenom_user(String prenom_user) {
		this.prenom_user = prenom_user;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getDatenaiss() {
		return datenaiss;
	}
	public void setDatenaiss(Date datenaiss) {
		this.datenaiss = datenaiss;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getSituation_matri() {
		return situation_matri;
	}
	public void setSituation_matri(String situation_matri) {
		this.situation_matri = situation_matri;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Documents> getDocumentss() {
		return documentss;
	}
	public void setDocumentss(Collection<Documents> documentss) {
		this.documentss = documentss;
	}
	public Collection<DroitAttribues> getDroitattribues() {
		return droitattribues;
	}
	public void setDroitattribues(Collection<DroitAttribues> droitattribues) {
		this.droitattribues = droitattribues;
	}
	

}
