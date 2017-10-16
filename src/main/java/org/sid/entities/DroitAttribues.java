package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class DroitAttribues implements Serializable{
	@Id @GeneratedValue
	private Long iddroitattribues;
@ManyToOne
@JoinColumn(name="code_doc")
private Documents documentss;
@ManyToOne
@JoinColumn(name="code_droit")
private DroitDacces droitdaccess;
private Date datedeb;
private Date datefin;
@ManyToOne
@JoinColumn(name="code_user")
private Utilisateur utilisateurs;
public DroitAttribues() {
	super();
	// TODO Auto-generated constructor stub
}

public DroitAttribues(Documents documentss, DroitDacces droitdaccess, Date datedeb, Date datefin,
		Utilisateur utilisateurs) {
	super();
	this.documentss = documentss;
	this.droitdaccess = droitdaccess;
	this.datedeb = datedeb;
	this.datefin = datefin;
	this.utilisateurs = utilisateurs;
}

public Documents getDocumentss() {
	return documentss;
}
public void setDocumentss(Documents documentss) {
	this.documentss = documentss;
}
public DroitDacces getDroitdaccess() {
	return droitdaccess;
}
public void setDroitdaccess(DroitDacces droitdaccess) {
	this.droitdaccess = droitdaccess;
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
public Utilisateur getUtilisateurs() {
	return utilisateurs;
}
public void setUtilisateurs(Utilisateur utilisateurs) {
	this.utilisateurs = utilisateurs;
}

}
