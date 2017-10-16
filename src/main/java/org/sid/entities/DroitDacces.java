package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class DroitDacces implements Serializable{
	@Id @GeneratedValue
private Long iddroit;
private String codedroit;
private String libelle;
@OneToMany(mappedBy="droitdaccess", fetch= FetchType.LAZY)
private Collection<DroitAttribues> droitattribuess;
public DroitDacces() {
	super();
	// TODO Auto-generated constructor stub
}
public DroitDacces(String codedroit, String libelle) {
	super();
	this.codedroit = codedroit;
	this.libelle = libelle;
}
public Long getIddroit() {
	return iddroit;
}
public void setIddroit(Long iddroit) {
	this.iddroit = iddroit;
}
public String getCodedroit() {
	return codedroit;
}
public void setCodedroit(String codedroit) {
	this.codedroit = codedroit;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public Collection<DroitAttribues> getDroitattribues() {
	return droitattribuess;
}
public void setDroitattribues(Collection<DroitAttribues> droitattribuess) {
	this.droitattribuess = droitattribuess;
}



}
