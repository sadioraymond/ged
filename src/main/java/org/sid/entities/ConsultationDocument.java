package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ConsultationDocument implements Serializable {
	@Id @GeneratedValue
	private Long id_consultation;
	@ManyToOne
	@JoinColumn(name="id_doc")
	private Documents documentss;
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateurs;
	private Date datedeb;
	private Date datefin;
	public ConsultationDocument() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConsultationDocument(Documents documentss, Utilisateur utilisateurs, Date datedeb, Date datefin) {
		super();
		this.documentss = documentss;
		this.utilisateurs = utilisateurs;
		this.datedeb = datedeb;
		this.datefin = datefin;
	}
	public Long getId_consultation() {
		return id_consultation;
	}
	public void setId_consultation(Long id_consultation) {
		this.id_consultation = id_consultation;
	}
	public Documents getDocumentss() {
		return documentss;
	}
	public void setDocumentss(Documents documentss) {
		this.documentss = documentss;
	}
	public Utilisateur getUtilisateurs() {
		return utilisateurs;
	}
	public void setUtilisateurs(Utilisateur utilisateurs) {
		this.utilisateurs = utilisateurs;
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
