package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type_Documents",discriminatorType=DiscriminatorType.STRING,length=4)
public abstract class Documents implements Serializable {
	@Id @GeneratedValue
private Long iddoc;
private Date datecreation;
@OneToMany(mappedBy="documentss", fetch= FetchType.LAZY)
private Collection<DroitAttribues> droitattribuess;
@ManyToOne
@JoinColumn(name="id_utilisateurcreateur")
private Utilisateur usercreateur;
@OneToMany(mappedBy="documentss", fetch= FetchType.LAZY)
private Collection<ConsultationDocument> consultationdocuments;
private String titre;
private String nomfichier;
private int etat;
public Documents() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getIddoc() {
	return iddoc;
}

public int getEtat() {
	return etat;
}
public void setEtat(int etat) {
	this.etat = etat;
}
public String getNomfichier() {
	return nomfichier;
}
public void setNomfichier(String nomfichier) {
	this.nomfichier = nomfichier;
}
public Utilisateur getUsercreateur() {
	return usercreateur;
}
public void setUsercreateur(Utilisateur usercreateur) {
	this.usercreateur = usercreateur;
}
public String getTitre() {
	return titre;
}
public void setTitre(String titre) {
	this.titre = titre;
}
public void setIddoc(Long iddoc) {
	this.iddoc = iddoc;
}
public Date getDatecreation() {
	return datecreation;
}
public void setDatecreation(Date datecreation) {
	this.datecreation = datecreation;
}
public Collection<DroitAttribues> getDroitattribues() {
	return droitattribuess;
}
public void setDroitattribues(Collection<DroitAttribues> droitattribuess) {
	this.droitattribuess = droitattribuess;
}
public Collection<DroitAttribues> getDroitattribuess() {
	return droitattribuess;
}
public void setDroitattribuess(Collection<DroitAttribues> droitattribuess) {
	this.droitattribuess = droitattribuess;
}
public Utilisateur getUtilisateurs() {
	return usercreateur;
}
public void setUtilisateurs(Utilisateur utilisateurs) {
	this.usercreateur = utilisateurs;
}
public Documents(Date datecreation, Utilisateur usercreateur) {
	super();
	this.datecreation = datecreation;
	this.usercreateur = usercreateur;
}
public Collection<ConsultationDocument> getConsultationdocuments() {
	return consultationdocuments;
}
public void setConsultationdocuments(Collection<ConsultationDocument> consultationdocuments) {
	this.consultationdocuments = consultationdocuments;
}




}
