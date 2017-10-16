package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Documents implements Serializable {
	@Id @GeneratedValue
private Long iddoc;
private Date datecreation;
@OneToMany(mappedBy="documentss", fetch= FetchType.LAZY)
private Collection<DroitAttribues> droitattribuess;
@ManyToOne
@JoinColumn(name="id_utilisateur")
private Utilisateur utilisateurs;
public Documents() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getIddoc() {
	return iddoc;
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
	return utilisateurs;
}
public void setUtilisateurs(Utilisateur utilisateurs) {
	this.utilisateurs = utilisateurs;
}
public Documents(Date datecreation, Utilisateur utilisateurs) {
	super();
	this.datecreation = datecreation;
	this.utilisateurs = utilisateurs;
}



}
